package com.artbridge.artist.presentation.rest;

import com.artbridge.artist.domain.model.Like;
import com.artbridge.artist.presentation.exception.BadRequestAlertException;
import com.artbridge.artist.infrastructure.repository.LikeRepository;
import com.artbridge.artist.infrastructure.security.SecurityUtils;
import com.artbridge.artist.infrastructure.security.jwt.TokenProvider;
import com.artbridge.artist.application.usecase.LikeUsecase;
import com.artbridge.artist.application.dto.LikeDTO;
import com.artbridge.artist.application.dto.MemberDTO;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link Like}.
 */
@RestController
@RequestMapping("/api")
public class LikeResource {

    private final Logger log = LoggerFactory.getLogger(LikeResource.class);

    private static final String ENTITY_NAME = "artistLike";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LikeUsecase likeUsecase;

    private final LikeRepository likeRepository;
    private final TokenProvider tokenProvider;

    public LikeResource(LikeUsecase likeUsecase, LikeRepository likeRepository, TokenProvider tokenProvider) {
        this.likeUsecase = likeUsecase;
        this.likeRepository = likeRepository;
        this.tokenProvider = tokenProvider;
    }

    /**
     * {@code POST  /likes} : 좋아요를 생성합니다.
     *
     * @param likeDTO 좋아요 정보 (LikeDTO)
     * @return 생성된 좋아요 정보 (LikeDTO)를 담은 ResponseEntity 객체
     * @throws URISyntaxException URI 문법이 잘못된 경우 발생하는 예외
     */
    @PostMapping("/likes")
    public ResponseEntity<LikeDTO> createLike(@RequestBody LikeDTO likeDTO) throws URISyntaxException {
        log.debug("REST request to save Like : {}", likeDTO);
        if (likeDTO.getId() != null) {
            throw new BadRequestAlertException("A new like cannot already have an ID", ENTITY_NAME, "idexists");
        }

        String token = this.validateAndGetToken();

        MemberDTO memberDTO = this.createMember(token);

        likeDTO.setMemberDTO(memberDTO);

        LikeDTO result = likeUsecase.save(likeDTO);
        return ResponseEntity.created(new URI("/api/likes/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString())).body(result);
    }

    /**
     * {@code PUT  /likes/:id} : Updates an existing like.
     *
     * @param id      the id of the likeDTO to save.
     * @param likeDTO the likeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated likeDTO,
     * or with status {@code 400 (Bad Request)} if the likeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the likeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/likes/{id}")
    public ResponseEntity<LikeDTO> updateLike(@PathVariable(value = "id", required = false) final Long id, @RequestBody LikeDTO likeDTO) throws URISyntaxException {
        log.debug("REST request to update Like : {}, {}", id, likeDTO);
        if (likeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, likeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!likeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        LikeDTO result = likeUsecase.update(likeDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, likeDTO.getId().toString())).body(result);
    }

    /**
     * {@code PATCH  /likes/:id} : Partial updates given fields of an existing like, field will ignore if it is null
     *
     * @param id      the id of the likeDTO to save.
     * @param likeDTO the likeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated likeDTO,
     * or with status {@code 400 (Bad Request)} if the likeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the likeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the likeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/likes/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<LikeDTO> partialUpdateLike(@PathVariable(value = "id", required = false) final Long id, @RequestBody LikeDTO likeDTO) throws URISyntaxException {
        log.debug("REST request to partial update Like partially : {}, {}", id, likeDTO);
        if (likeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, likeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!likeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LikeDTO> result = likeUsecase.partialUpdate(likeDTO);

        return ResponseUtil.wrapOrNotFound(result, HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, likeDTO.getId().toString()));
    }

    /**
     * {@code GET  /likes} : get all the likes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of likes in body.
     */
    @GetMapping("/likes")
    public ResponseEntity<List<LikeDTO>> getAllLikes(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Likes");
        Page<LikeDTO> page = likeUsecase.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /likes/:id} : get the "id" like.
     *
     * @param id the id of the likeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the likeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/likes/{id}")
    public ResponseEntity<LikeDTO> getLike(@PathVariable Long id) {
        log.debug("REST request to get Like : {}", id);
        Optional<LikeDTO> likeDTO = likeUsecase.findOne(id);
        return ResponseUtil.wrapOrNotFound(likeDTO);
    }

    /**
     * {@code GET  /likes/counts} : 작품에 대한 좋아요 개수를 조회합니다.
     *
     * 주어진 작품 ID에 해당하는 작품의 좋아요 개수를 조회합니다.
     *
     * @param artistId 작품 ID (Long)
     * @return 작품에 대한 좋아요 개수 (Long)
     */
    @GetMapping("/likes/counts")
    public ResponseEntity<Long> getLikeCount(@RequestParam Long artistId) {
        log.debug("REST request to get Like Count : {}", artistId);
        Long count = likeUsecase.countByArtistId(artistId);
        return ResponseEntity.ok().body(count);
    }


    /**
     * {@code DELETE  /likes/:id} : "id"에 해당하는 좋아요를 삭제합니다.
     *
     * @param artistId 좋아요 ID (Long)
     * @return {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/likes/{id}")
    public ResponseEntity<Void> deleteLike(@PathVariable(value = "id") Long artistId) {
        log.debug("REST request to delete Like : {}", artistId);
        String token = this.validateAndGetToken();
        MemberDTO memberDTO = this.createMember(token);

        likeUsecase.delete(artistId, memberDTO.getId());
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, artistId.toString()))
            .build();
    }





    /**
     * 현재 사용자로부터 얻은 JWT 토큰을 유효성 검사하고 유효한 토큰을 반환합니다.
     *
     * @return 유효한 JWT 토큰
     * @throws BadRequestAlertException JWT 토큰이 잘못되었거나 존재하지 않는 경우
     */
    private String validateAndGetToken() { /*TODO -REFACTOR*/
        Optional<String> optToken = SecurityUtils.getCurrentUserJWT();
        if (optToken.isEmpty() || !this.tokenProvider.validateToken(optToken.get())) {
            throw new BadRequestAlertException("Invalid JWT token", ENTITY_NAME, "invalidtoken");
        }
        return optToken.get();
    }


    /**
     * 주어진 토큰을 사용하여 MemberDTO 객체를 생성합니다.
     *
     * @param token JWT 토큰
     * @return MemberDTO 객체
     */
    private MemberDTO createMember(String token) { /*TODO -REFACTOR*/
        Authentication authentication = this.tokenProvider.getAuthentication(token);
        Long userId = this.tokenProvider.getUserIdFromToken(token);
        return new MemberDTO(userId,  authentication.getName());
    }
}
