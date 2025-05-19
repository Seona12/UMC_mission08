package com.example.demo.domain.mapping;

import com.example.demo.domain.Member;
import com.example.demo.domain.foodCategory;
import jakarta.persistence.*;
import lombok.*;
import com.example.demo.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberPrefer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private foodCategory foodCategory;

    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getMemberPreferList().remove(this);
        }
        this.member = member;
        member.getMemberPreferList().add(this);
    }

    // 타입과 변수명, setter 모두 소문자 클래스명에 맞춤
    public void setFoodCategory(foodCategory foodCategory) {
        this.foodCategory = foodCategory;
    }
}
