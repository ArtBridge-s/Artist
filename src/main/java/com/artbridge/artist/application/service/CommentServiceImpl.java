package com.artbridge.artist.application.service;

import com.artbridge.artist.infrastructure.messaging.MemberProducer;
import com.artbridge.artist.domain.model.Comment;
import com.artbridge.artist.infrastructure.repository.CommentRepository;
import com.artbridge.artist.application.dto.CommentDTO;
import com.artbridge.artist.application.mapper.CommentMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Comment}.
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);

    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    private final MemberProducer memberProducer;

    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper, MemberProducer memberProducer) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.memberProducer = memberProducer;
    }

    @Override
    public CommentDTO save(CommentDTO commentDTO) {
        log.debug("Request to save Comment : {}", commentDTO);
        Comment comment = commentMapper.toEntity(commentDTO);

//        this.memberProducer.requestMemberName(comment.getMemberId());
        comment = commentRepository.save(comment);
        return commentMapper.toDto(comment);
    }

    @Override
    public CommentDTO update(CommentDTO commentDTO) {
        log.debug("Request to update Comment : {}", commentDTO);
        Comment comment = commentMapper.toEntity(commentDTO);
        comment = commentRepository.save(comment);
        return commentMapper.toDto(comment);
    }

    @Override
    public Optional<CommentDTO> partialUpdate(CommentDTO commentDTO) {
        log.debug("Request to partially update Comment : {}", commentDTO);

        return commentRepository
            .findById(commentDTO.getId())
            .map(existingComment -> {
                commentMapper.partialUpdate(existingComment, commentDTO);

                return existingComment;
            })
            .map(commentRepository::save)
            .map(commentMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CommentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Comments");
        return commentRepository.findAll(pageable).map(commentMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CommentDTO> findOne(Long id) {
        log.debug("Request to get Comment : {}", id);
        return commentRepository.findById(id).map(commentMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Comment : {}", id);
        commentRepository.deleteById(id);
    }

    @Override
    public Page<CommentDTO> findByArtistId(Pageable pageable, Long artistId) {
        log.debug("Request to get all Comments");
        return commentRepository.findByArtist_Id(pageable, artistId).map(commentMapper::toDto);
    }
}
