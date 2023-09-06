package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    public void discount() {
        Member member = new Member(1L, "VIP", Grade.VIP);
        Member member1 = new Member(1L, "NOT_VIP", Grade.BASIC);

        assertThat(discountPolicy.discount(member, 10000)).isEqualTo(1000);
        assertThat(discountPolicy.discount(member1, 10000)).isEqualTo(0);
    }
}
