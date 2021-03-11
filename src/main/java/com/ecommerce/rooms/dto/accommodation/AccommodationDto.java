package com.ecommerce.rooms.dto.accommodation;

import com.ecommerce.rooms.domain.Accommodation.Accommodation;
import com.ecommerce.rooms.dto.RoomDto;
import java.util.List;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
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

    private List<RoomDto> rooms;

    public AccommodationDto(String name, String phone, String address, String detailedAddress,
        String notice, String basicInfo, String addPersonnelInfo, String refundPolicy,
        String etc, String nearbyInfo) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.detailedAddress = detailedAddress;
        this.notice = notice;
        this.basicInfo = basicInfo;
        this.addPersonnelInfo = addPersonnelInfo;
        this.refundPolicy = refundPolicy;
        this.etc = etc;
        this.nearbyInfo = nearbyInfo;
    }

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
        this.rooms = RoomDto.toList(accommodation.getRooms());
    }
}
