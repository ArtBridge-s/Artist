package com.artbridge.artist.web.rest;

import com.artbridge.artist.adapter.GCSService;
import com.artbridge.artist.repository.ArtistRepository;
import com.artbridge.artist.security.SecurityUtils;
import com.artbridge.artist.security.jwt.TokenProvider;
import com.artbridge.artist.service.ArtistService;
import com.artbridge.artist.service.dto.ArtistDTO;
import com.artbridge.artist.service.dto.MemberDTO;
import com.artbridge.artist.web.rest.errors.BadRequestAlertException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.artbridge.artist.domain.Artist}.
 */
@RestController
@RequestMapping("/api")
public class ArtistResource {

    private final Logger log = LoggerFactory.getLogger(ArtistResource.class);

    private static final String ENTITY_NAME = "artistArtist";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ArtistService artistService;

    private final ArtistRepository artistRepository;

    private final TokenProvider tokenProvider;

    private final GCSService gcsService;

    public ArtistResource(ArtistService artistService, ArtistRepository artistRepository, TokenProvider tokenProvider, GCSService gcsService) {
        this.artistService = artistService;
        this.artistRepository = artistRepository;
        this.tokenProvider = tokenProvider;
        this.gcsService = gcsService;
    }

    /**
     * 아티스트를 생성합니다.
     *
     * @param file 이미지 파일 (MultipartFile)
     * @param artistDTOStr 아티스트 정보 (JSON 문자열)
     * @return 생성된 아티스트의 정보를 담은 ArtistDTO 객체
     * @throws URISyntaxException URI 구문 오류가 발생한 경우
     * @throws JsonProcessingException JSON 처리 오류가 발생한 경우
     */
    @PostMapping
    public ResponseEntity<ArtistDTO> createArtist(@RequestParam("image") MultipartFile file, @RequestParam("artistDTO") String artistDTOStr) throws URISyntaxException, JsonProcessingException { /*TODO: 엔드포인트 수정*/
        ArtistDTO artistDTO = this.convertToDTO(artistDTOStr);

        log.debug("REST request to save Artist : {}", artistDTO);
        if (artistDTO.getId() != null) {
            throw new BadRequestAlertException("A new artist cannot already have an ID", ENTITY_NAME, "idexists");
        }

        String token = this.validateAndGetToken();

        MemberDTO memberDTO = this.createMember(token);
        artistDTO.setMemberDTO(memberDTO);

        this.uploadImage(file, artistDTO);

        ArtistDTO result = artistService.save(artistDTO);
        return ResponseEntity
            .created(new URI("/api/artists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /artists/:id} : Updates an existing artist.
     *
     * @param id the id of the artistDTO to save.
     * @param artistDTO the artistDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated artistDTO,
     * or with status {@code 400 (Bad Request)} if the artistDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the artistDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/artists/{id}")
    public ResponseEntity<ArtistDTO> updateArtist(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ArtistDTO artistDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Artist : {}, {}", id, artistDTO);

        this.validateArtist(id, artistDTO);

        ArtistDTO result = artistService.update(artistDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, artistDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /artists/:id} : Partial updates given fields of an existing artist, field will ignore if it is null
     *
     * @param id the id of the artistDTO to save.
     * @param artistDTO the artistDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated artistDTO,
     * or with status {@code 400 (Bad Request)} if the artistDTO is not valid,
     * or with status {@code 404 (Not Found)} if the artistDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the artistDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/artists/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ArtistDTO> partialUpdateArtist(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ArtistDTO artistDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Artist partially : {}, {}", id, artistDTO);

        this.validateArtist(id, artistDTO);

        Optional<ArtistDTO> result = artistService.partialUpdate(artistDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, artistDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /artists} : get all the artists.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of artists in body.
     */
    @GetMapping("/artists")
    public ResponseEntity<List<ArtistDTO>> getAllArtists(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Artists");
        Page<ArtistDTO> page = artistService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /artists/:id} : get the "id" artist.
     *
     * @param id the id of the artistDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the artistDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/artists/{id}")
    public ResponseEntity<ArtistDTO> getArtist(@PathVariable Long id) {
        log.debug("REST request to get Artist : {}", id);
        Optional<ArtistDTO> artistDTO = artistService.findOne(id);
        return ResponseUtil.wrapOrNotFound(artistDTO);
    }

    /**
     * {@code DELETE  /artists/:id} : delete the "id" artist.
     *
     * @param id the id of the artistDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/artists/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
        log.debug("REST request to delete Artist : {}", id);
        artistService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }



    /**
     * 업로드된 이미지 파일을 처리하여 ArtistDTO에 이미지 URL을 설정합니다.
     *
     * @param file       업로드된 이미지 파일
     * @param artistDTO ArtworkDTO 객체
     * @throws BadRequestAlertException 파일 업로드 실패 시 발생하는 예외
     */
    private void uploadImage(MultipartFile file, ArtistDTO artistDTO) {
        log.debug("REST request to upload image file : {}", file);
        if (!Objects.isNull(file)) {
            try {
                String imageUrl = gcsService.uploadImageToGCS(file);
                artistDTO.setImgUrl(imageUrl);
            } catch (IOException e) {
                throw new BadRequestAlertException("File upload failed", ENTITY_NAME, "filereadfailed");
            }
        }
    }

    /**
     * 현재 사용자로부터 얻은 JWT 토큰을 유효성 검사하고 유효한 토큰을 반환합니다.
     *
     * @return 유효한 JWT 토큰
     * @throws BadRequestAlertException JWT 토큰이 잘못되었거나 존재하지 않는 경우
     */
    private String validateAndGetToken() {/*TODO -REFACTOR*/
        Optional<String> optToken = SecurityUtils.getCurrentUserJWT();
        if (optToken.isEmpty() || !this.tokenProvider.validateToken(optToken.get())) {
            throw new BadRequestAlertException("Invalid JWT token", ENTITY_NAME, "invalidtoken");
        }
        return optToken.get();
    }



    /**
     * ArtworkDTO를 JSON 문자열 표현에서 실제 ArtworkDTO 객체로 변환합니다.
     *
     * @param artistDTOStr ArtistDTO의 JSON 문자열 표현
     * @return ArtistDTO 객체
     * @throws JsonProcessingException JSON 처리 중 오류가 발생한 경우
     */
    private ArtistDTO convertToDTO(String artistDTOStr) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(artistDTOStr, ArtistDTO.class);
    }


    /**
     * 주어진 토큰을 사용하여 MemberDTO 객체를 생성합니다.
     *
     * @param token JWT 토큰
     * @return MemberDTO 객체
     */
    private MemberDTO createMember(String token) {
        Authentication authentication = this.tokenProvider.getAuthentication(token);
        Long userId = this.tokenProvider.getUserIdFromToken(token);
        return new MemberDTO(userId, authentication.getName());
    }

    /**
     * Artist의 유효성을 검사합니다.
     *
     * @param id Artist의 식별자(ID)
     * @param artistDTO Artist의 정보를 담은 ArtistDTO 객체
     * @throws BadRequestAlertException Artist의 유효성 검사에 실패한 경우 발생하는 BadRequestAlertException
     */
    private void validateArtist(Long id, ArtistDTO artistDTO) {
        if (artistDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, artistDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }
        if (!artistRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
    }
}
