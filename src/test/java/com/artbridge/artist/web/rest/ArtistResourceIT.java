package com.artbridge.artist.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.artbridge.artist.IntegrationTest;
import com.artbridge.artist.domain.Artist;
import com.artbridge.artist.domain.enumeration.Status;
import com.artbridge.artist.repository.ArtistRepository;
import com.artbridge.artist.service.dto.ArtistDTO;
import com.artbridge.artist.service.mapper.ArtistMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ArtistResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ArtistResourceIT {

//    private static final String DEFAULT_NAME = "AAAAAAAAAA";
//    private static final String UPDATED_NAME = "BBBBBBBBBB";
//
//    private static final String DEFAULT_REAL_NAME = "AAAAAAAAAA";
//    private static final String UPDATED_REAL_NAME = "BBBBBBBBBB";
//
//    private static final String DEFAULT_IMG_URL = "AAAAAAAAAA";
//    private static final String UPDATED_IMG_URL = "BBBBBBBBBB";
//
//    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
//    private static final String UPDATED_PHONE = "BBBBBBBBBB";
//
//    private static final String DEFAULT_CAREER = "AAAAAAAAAA";
//    private static final String UPDATED_CAREER = "BBBBBBBBBB";
//
//    private static final String DEFAULT_VO_ARTWORK = "AAAAAAAAAA";
//    private static final String UPDATED_VO_ARTWORK = "BBBBBBBBBB";
//
//    private static final String DEFAULT_VO_MEMBER = "AAAAAAAAAA";
//    private static final String UPDATED_VO_MEMBER = "BBBBBBBBBB";
//
//    private static final Status DEFAULT_STATUS = Status.UPLOAD_PENDING;
//    private static final Status UPDATED_STATUS = Status.REVISION_PENDING;
//
//    private static final String ENTITY_API_URL = "/api/artists";
//    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
//
//    private static Random random = new Random();
//    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
//
//    @Autowired
//    private ArtistRepository artistRepository;
//
//    @Autowired
//    private ArtistMapper artistMapper;
//
//    @Autowired
//    private EntityManager em;
//
//    @Autowired
//    private MockMvc restArtistMockMvc;
//
//    private Artist artist;
//
//    /**
//     * Create an entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//    public static Artist createEntity(EntityManager em) {
//        Artist artist = new Artist()
//            .name(DEFAULT_NAME)
//            .realName(DEFAULT_REAL_NAME)
//            .imgUrl(DEFAULT_IMG_URL)
//            .phone(DEFAULT_PHONE)
//            .career(DEFAULT_CAREER)
//            .voArtwork(DEFAULT_VO_ARTWORK)
//            .voMember(DEFAULT_VO_MEMBER)
//            .status(DEFAULT_STATUS);
//        return artist;
//    }
//
//    /**
//     * Create an updated entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//    public static Artist createUpdatedEntity(EntityManager em) {
//        Artist artist = new Artist()
//            .name(UPDATED_NAME)
//            .realName(UPDATED_REAL_NAME)
//            .imgUrl(UPDATED_IMG_URL)
//            .phone(UPDATED_PHONE)
//            .career(UPDATED_CAREER)
//            .voArtwork(UPDATED_VO_ARTWORK)
//            .voMember(UPDATED_VO_MEMBER)
//            .status(UPDATED_STATUS);
//        return artist;
//    }
//
//    @BeforeEach
//    public void initTest() {
//        artist = createEntity(em);
//    }
//
//    @Test
//    @Transactional
//    void createArtist() throws Exception {
//        int databaseSizeBeforeCreate = artistRepository.findAll().size();
//        // Create the Artist
//        ArtistDTO artistDTO = artistMapper.toDto(artist);
//        restArtistMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(artistDTO)))
//            .andExpect(status().isCreated());
//
//        // Validate the Artist in the database
//        List<Artist> artistList = artistRepository.findAll();
//        assertThat(artistList).hasSize(databaseSizeBeforeCreate + 1);
//        Artist testArtist = artistList.get(artistList.size() - 1);
//        assertThat(testArtist.getName()).isEqualTo(DEFAULT_NAME);
//        assertThat(testArtist.getRealName()).isEqualTo(DEFAULT_REAL_NAME);
//        assertThat(testArtist.getImgUrl()).isEqualTo(DEFAULT_IMG_URL);
//        assertThat(testArtist.getPhone()).isEqualTo(DEFAULT_PHONE);
//        assertThat(testArtist.getCareer()).isEqualTo(DEFAULT_CAREER);
//        assertThat(testArtist.getVoArtwork()).isEqualTo(DEFAULT_VO_ARTWORK);
//        assertThat(testArtist.getVoMember()).isEqualTo(DEFAULT_VO_MEMBER);
//        assertThat(testArtist.getStatus()).isEqualTo(DEFAULT_STATUS);
//    }
//
//    @Test
//    @Transactional
//    void createArtistWithExistingId() throws Exception {
//        // Create the Artist with an existing ID
//        artist.setId(1L);
//        ArtistDTO artistDTO = artistMapper.toDto(artist);
//
//        int databaseSizeBeforeCreate = artistRepository.findAll().size();
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restArtistMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(artistDTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Artist in the database
//        List<Artist> artistList = artistRepository.findAll();
//        assertThat(artistList).hasSize(databaseSizeBeforeCreate);
//    }
//
//    @Test
//    @Transactional
//    void getAllArtists() throws Exception {
//        // Initialize the database
//        artistRepository.saveAndFlush(artist);
//
//        // Get all the artistList
//        restArtistMockMvc
//            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(artist.getId().intValue())))
//            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
//            .andExpect(jsonPath("$.[*].realName").value(hasItem(DEFAULT_REAL_NAME)))
//            .andExpect(jsonPath("$.[*].imgUrl").value(hasItem(DEFAULT_IMG_URL)))
//            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
//            .andExpect(jsonPath("$.[*].career").value(hasItem(DEFAULT_CAREER)))
//            .andExpect(jsonPath("$.[*].voArtwork").value(hasItem(DEFAULT_VO_ARTWORK)))
//            .andExpect(jsonPath("$.[*].voMember").value(hasItem(DEFAULT_VO_MEMBER)))
//            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
//    }
//
//    @Test
//    @Transactional
//    void getArtist() throws Exception {
//        // Initialize the database
//        artistRepository.saveAndFlush(artist);
//
//        // Get the artist
//        restArtistMockMvc
//            .perform(get(ENTITY_API_URL_ID, artist.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.id").value(artist.getId().intValue()))
//            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
//            .andExpect(jsonPath("$.realName").value(DEFAULT_REAL_NAME))
//            .andExpect(jsonPath("$.imgUrl").value(DEFAULT_IMG_URL))
//            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
//            .andExpect(jsonPath("$.career").value(DEFAULT_CAREER))
//            .andExpect(jsonPath("$.voArtwork").value(DEFAULT_VO_ARTWORK))
//            .andExpect(jsonPath("$.voMember").value(DEFAULT_VO_MEMBER))
//            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
//    }
//
//    @Test
//    @Transactional
//    void getNonExistingArtist() throws Exception {
//        // Get the artist
//        restArtistMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    void putExistingArtist() throws Exception {
//        // Initialize the database
//        artistRepository.saveAndFlush(artist);
//
//        int databaseSizeBeforeUpdate = artistRepository.findAll().size();
//
//        // Update the artist
//        Artist updatedArtist = artistRepository.findById(artist.getId()).get();
//        // Disconnect from session so that the updates on updatedArtist are not directly saved in db
//        em.detach(updatedArtist);
//        updatedArtist
//            .name(UPDATED_NAME)
//            .realName(UPDATED_REAL_NAME)
//            .imgUrl(UPDATED_IMG_URL)
//            .phone(UPDATED_PHONE)
//            .career(UPDATED_CAREER)
//            .voArtwork(UPDATED_VO_ARTWORK)
//            .voMember(UPDATED_VO_MEMBER)
//            .status(UPDATED_STATUS);
//        ArtistDTO artistDTO = artistMapper.toDto(updatedArtist);
//
//        restArtistMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, artistDTO.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(artistDTO))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the Artist in the database
//        List<Artist> artistList = artistRepository.findAll();
//        assertThat(artistList).hasSize(databaseSizeBeforeUpdate);
//        Artist testArtist = artistList.get(artistList.size() - 1);
//        assertThat(testArtist.getName()).isEqualTo(UPDATED_NAME);
//        assertThat(testArtist.getRealName()).isEqualTo(UPDATED_REAL_NAME);
//        assertThat(testArtist.getImgUrl()).isEqualTo(UPDATED_IMG_URL);
//        assertThat(testArtist.getPhone()).isEqualTo(UPDATED_PHONE);
//        assertThat(testArtist.getCareer()).isEqualTo(UPDATED_CAREER);
//        assertThat(testArtist.getVoArtwork()).isEqualTo(UPDATED_VO_ARTWORK);
//        assertThat(testArtist.getVoMember()).isEqualTo(UPDATED_VO_MEMBER);
//        assertThat(testArtist.getStatus()).isEqualTo(UPDATED_STATUS);
//    }
//
//    @Test
//    @Transactional
//    void putNonExistingArtist() throws Exception {
//        int databaseSizeBeforeUpdate = artistRepository.findAll().size();
//        artist.setId(count.incrementAndGet());
//
//        // Create the Artist
//        ArtistDTO artistDTO = artistMapper.toDto(artist);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restArtistMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, artistDTO.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(artistDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the Artist in the database
//        List<Artist> artistList = artistRepository.findAll();
//        assertThat(artistList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void putWithIdMismatchArtist() throws Exception {
//        int databaseSizeBeforeUpdate = artistRepository.findAll().size();
//        artist.setId(count.incrementAndGet());
//
//        // Create the Artist
//        ArtistDTO artistDTO = artistMapper.toDto(artist);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restArtistMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(artistDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the Artist in the database
//        List<Artist> artistList = artistRepository.findAll();
//        assertThat(artistList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void putWithMissingIdPathParamArtist() throws Exception {
//        int databaseSizeBeforeUpdate = artistRepository.findAll().size();
//        artist.setId(count.incrementAndGet());
//
//        // Create the Artist
//        ArtistDTO artistDTO = artistMapper.toDto(artist);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restArtistMockMvc
//            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(artistDTO)))
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the Artist in the database
//        List<Artist> artistList = artistRepository.findAll();
//        assertThat(artistList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void partialUpdateArtistWithPatch() throws Exception {
//        // Initialize the database
//        artistRepository.saveAndFlush(artist);
//
//        int databaseSizeBeforeUpdate = artistRepository.findAll().size();
//
//        // Update the artist using partial update
//        Artist partialUpdatedArtist = new Artist();
//        partialUpdatedArtist.setId(artist.getId());
//
//        partialUpdatedArtist.realName(UPDATED_REAL_NAME).phone(UPDATED_PHONE);
//
//        restArtistMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, partialUpdatedArtist.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedArtist))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the Artist in the database
//        List<Artist> artistList = artistRepository.findAll();
//        assertThat(artistList).hasSize(databaseSizeBeforeUpdate);
//        Artist testArtist = artistList.get(artistList.size() - 1);
//        assertThat(testArtist.getName()).isEqualTo(DEFAULT_NAME);
//        assertThat(testArtist.getRealName()).isEqualTo(UPDATED_REAL_NAME);
//        assertThat(testArtist.getImgUrl()).isEqualTo(DEFAULT_IMG_URL);
//        assertThat(testArtist.getPhone()).isEqualTo(UPDATED_PHONE);
//        assertThat(testArtist.getCareer()).isEqualTo(DEFAULT_CAREER);
//        assertThat(testArtist.getVoArtwork()).isEqualTo(DEFAULT_VO_ARTWORK);
//        assertThat(testArtist.getVoMember()).isEqualTo(DEFAULT_VO_MEMBER);
//        assertThat(testArtist.getStatus()).isEqualTo(DEFAULT_STATUS);
//    }
//
//    @Test
//    @Transactional
//    void fullUpdateArtistWithPatch() throws Exception {
//        // Initialize the database
//        artistRepository.saveAndFlush(artist);
//
//        int databaseSizeBeforeUpdate = artistRepository.findAll().size();
//
//        // Update the artist using partial update
//        Artist partialUpdatedArtist = new Artist();
//        partialUpdatedArtist.setId(artist.getId());
//
//        partialUpdatedArtist
//            .name(UPDATED_NAME)
//            .realName(UPDATED_REAL_NAME)
//            .imgUrl(UPDATED_IMG_URL)
//            .phone(UPDATED_PHONE)
//            .career(UPDATED_CAREER)
//            .voArtwork(UPDATED_VO_ARTWORK)
//            .voMember(UPDATED_VO_MEMBER)
//            .status(UPDATED_STATUS);
//
//        restArtistMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, partialUpdatedArtist.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedArtist))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the Artist in the database
//        List<Artist> artistList = artistRepository.findAll();
//        assertThat(artistList).hasSize(databaseSizeBeforeUpdate);
//        Artist testArtist = artistList.get(artistList.size() - 1);
//        assertThat(testArtist.getName()).isEqualTo(UPDATED_NAME);
//        assertThat(testArtist.getRealName()).isEqualTo(UPDATED_REAL_NAME);
//        assertThat(testArtist.getImgUrl()).isEqualTo(UPDATED_IMG_URL);
//        assertThat(testArtist.getPhone()).isEqualTo(UPDATED_PHONE);
//        assertThat(testArtist.getCareer()).isEqualTo(UPDATED_CAREER);
//        assertThat(testArtist.getVoArtwork()).isEqualTo(UPDATED_VO_ARTWORK);
//        assertThat(testArtist.getVoMember()).isEqualTo(UPDATED_VO_MEMBER);
//        assertThat(testArtist.getStatus()).isEqualTo(UPDATED_STATUS);
//    }
//
//    @Test
//    @Transactional
//    void patchNonExistingArtist() throws Exception {
//        int databaseSizeBeforeUpdate = artistRepository.findAll().size();
//        artist.setId(count.incrementAndGet());
//
//        // Create the Artist
//        ArtistDTO artistDTO = artistMapper.toDto(artist);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restArtistMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, artistDTO.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(artistDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the Artist in the database
//        List<Artist> artistList = artistRepository.findAll();
//        assertThat(artistList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void patchWithIdMismatchArtist() throws Exception {
//        int databaseSizeBeforeUpdate = artistRepository.findAll().size();
//        artist.setId(count.incrementAndGet());
//
//        // Create the Artist
//        ArtistDTO artistDTO = artistMapper.toDto(artist);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restArtistMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(artistDTO))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the Artist in the database
//        List<Artist> artistList = artistRepository.findAll();
//        assertThat(artistList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void patchWithMissingIdPathParamArtist() throws Exception {
//        int databaseSizeBeforeUpdate = artistRepository.findAll().size();
//        artist.setId(count.incrementAndGet());
//
//        // Create the Artist
//        ArtistDTO artistDTO = artistMapper.toDto(artist);
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restArtistMockMvc
//            .perform(
//                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(artistDTO))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the Artist in the database
//        List<Artist> artistList = artistRepository.findAll();
//        assertThat(artistList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void deleteArtist() throws Exception {
//        // Initialize the database
//        artistRepository.saveAndFlush(artist);
//
//        int databaseSizeBeforeDelete = artistRepository.findAll().size();
//
//        // Delete the artist
//        restArtistMockMvc
//            .perform(delete(ENTITY_API_URL_ID, artist.getId()).accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isNoContent());
//
//        // Validate the database contains one less item
//        List<Artist> artistList = artistRepository.findAll();
//        assertThat(artistList).hasSize(databaseSizeBeforeDelete - 1);
//    }
}
