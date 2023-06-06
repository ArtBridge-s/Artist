package com.artbridge.artist.domain.service;

import com.artbridge.artist.domain.model.Artist;

public interface ArtistService {

    /**
     * 아티스트의 업로드 대기 상태를 설정합니다.
     *
     * @param artist 업로드 대기 상태를 설정할 아티스트 객체
     */
    void setUploadPendingStatus(Artist artist);

    /**
     * 아티스트의 수정 대기 상태를 설정합니다.
     *
     * @param artist 수정 대기 상태를 설정할 아티스트 객체
     */
    void setRevisionPendingStatus(Artist artist);

    /**
     * 아티스트의 삭제 대기 상태를 설정합니다.
     *
     * @param artist 삭제 대기 상태를 설정할 아티스트 객체
     */
    void setDeletePendingStatus(Artist artist);

    /**
     * 아티스트의 승인 상태를 설정합니다.
     *
     * @param artist 승인 상태를 설정할 아티스트 객체
     */
    void setOkStatus(Artist artist);
}
