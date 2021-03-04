package com.ecommerce.rooms.dto.accommodation;

import com.ecommerce.rooms.domain.Accommodation.Accommodation;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AccommodationDto {

    // 이름
    private String name;

    // 전화 번호
    private String phone;

    // 주소
    private String address;

    // 상세 주소
    private String detailedAddress;

    // 공지 사항
    private String notice;

    // 기본 정보
    private String basicInfo;

    // 인원 추가 정보
    private String addPersonnelInfo;

    // 환불 규정
    private String refundPolicy;

    // 기타 사항
    private String etc;

    // 주변 정보
    private String nearbyInfo;

    public AccommodationDto(Accommodation accommodation) {
        this.name = accommodation.getName();
        this.phone = accommodation.getPhone();
        this.address = accommodation.getAddress();
        this.detailedAddress = accommodation.getDetailedAddress();
        this.notice = accommodation.getNotice();
        this.basicInfo = accommodation.getBasicInfo();
        this.addPersonnelInfo = accommodation.getAddPersonnelInfo();
        this.refundPolicy = accommodation.getRefundPolicy();
        this.etc = accommodation.getEtc();
        this.nearbyInfo = accommodation.getNearbyInfo();
    }
}
