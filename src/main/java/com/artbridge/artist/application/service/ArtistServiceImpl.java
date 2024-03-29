package com.artbridge.artist.application.service;

import com.artbridge.artist.domain.model.Artist;
import com.artbridge.artist.domain.standardType.Status;
import com.artbridge.artist.application.dto.ArtistDTO;
import com.artbridge.artist.application.mapper.ArtistMapper;
import java.util.Optional;

import com.artbridge.artist.infrastructure.messaging.MemberProducer;
import com.artbridge.artist.infrastructure.repository.ArtistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Artist}.
 */
@Service
@Transactional
public class ArtistServiceImpl implements ArtistService {

    private final Logger log = LoggerFactory.getLogger(ArtistServiceImpl.class);

    private final ArtistRepository artistRepository;

    private final ArtistMapper artistMapper;

    private final MemberProducer memberProducer;

    public ArtistServiceImpl(ArtistRepository artistRepository, ArtistMapper artistMapper, MemberProducer memberProducer) {
        this.artistRepository = artistRepository;
        this.artistMapper = artistMapper;
        this.memberProducer = memberProducer;
    }

    @Override
    public ArtistDTO save(ArtistDTO artistDTO) {
        log.debug("Request to save Artist : {}", artistDTO);
        Artist artist = artistMapper.toEntity(artistDTO);
        artist.setStatus(Status.UPLOAD_PENDING);

        memberProducer.requestMemberName(artist.getCreatedMember().getId());

        artist = artistRepository.save(artist);
        return artistMapper.toDto(artist);
    }

    @Override
    public ArtistDTO update(ArtistDTO artistDTO) {
        log.debug("Request to update Artist : {}", artistDTO);
        Artist artist = artistMapper.toEntity(artistDTO);
        artist.setStatus(Status.REVISION_PENDING);
        artist = artistRepository.save(artist);
        return artistMapper.toDto(artist);
    }

    @Override
    public Optional<ArtistDTO> partialUpdate(ArtistDTO artistDTO) {
        log.debug("Request to partially update Artist : {}", artistDTO);

        return artistRepository
            .findById(artistDTO.getId())
            .map(existingArtist -> {
                artistMapper.partialUpdate(existingArtist, artistDTO);

                return existingArtist;
            })
            .map(artistRepository::save)
            .map(artistMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ArtistDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Artists");
        return artistRepository.findAll(pageable).map(artistMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ArtistDTO> findOne(Long id) {
        log.debug("Request to get Artist : {}", id);
        return artistRepository.findById(id).map(artistMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Artist : {}", id);
        artistRepository.deleteById(id);
    }

    @Override
    public ArtistDTO deletePending(ArtistDTO artistDTO) {
        log.debug("Request to delete pending Artist : {}", artistDTO);
        Artist artist = artistMapper.toEntity(artistDTO);
        artist.setStatus(Status.DELETE_PENDING);
        artist = artistRepository.save(artist);
        return artistMapper.toDto(artist);
    }

    @Override
    public Page<ArtistDTO> findAllByStatus(Pageable pageable) {
        log.debug("Request to get all Artists by status: {}", Status.OK);
        return artistRepository.findAllByStatusOrderByIdDesc(pageable, Status.OK).map(artistMapper::toDto);
    }

    @Override
    public Page<ArtistDTO> findCreatePendings(Pageable pageable) {
        log.debug("Request to get create pending Artists");
        return artistRepository.findAllByStatusOrderByIdDesc(pageable, Status.UPLOAD_PENDING).map(artistMapper::toDto);
    }

    @Override
    public Page<ArtistDTO> findUpdatePendings(Pageable pageable) {
        log.debug("Request to get update pending Artists");
        return artistRepository.findAllByStatusOrderByIdDesc(pageable, Status.REVISION_PENDING).map(artistMapper::toDto);
    }

    @Override
    public Page<ArtistDTO> findDeletePendings(Pageable pageable) {
        log.debug("Request to get delete pending Artists");
        return artistRepository.findAllByStatusOrderByIdDesc(pageable, Status.DELETE_PENDING).map(artistMapper::toDto);
    }

    @Override
    public ArtistDTO authorizeOkArtist(Long id) {
        log.debug("Request to authorize ok artist : {}", id);
        return artistRepository.findById(id)
            .map(artist -> {
                artist.setStatus(Status.OK);
                return artistMapper.toDto(artistRepository.save(artist));
            })
            .orElseThrow();
    }

    @Override
    public void modifyMemberName(long id, String name) {
        log.debug("Request to modify member name : {}", id);
        artistRepository.findAllByCreatedMemberId(id).forEach(artist -> {
            artist.setCreatedMemberName(name);
            artistRepository.save(artist);
        });
    }



}
