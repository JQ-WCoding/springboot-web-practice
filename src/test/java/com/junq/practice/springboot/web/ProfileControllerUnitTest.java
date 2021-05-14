package com.junq.practice.springboot.web;

import org.junit.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfileControllerUnitTest {
    @Test
    public void real_Profile이_조회된다() {
        //given
        String expectedProfile = "real";
        MockEnvironment mockEnvironment = new MockEnvironment();
        mockEnvironment.addActiveProfile( expectedProfile );
        mockEnvironment.addActiveProfile( "oauth" );
        mockEnvironment.addActiveProfile( "real-do" );

        ProfileController controller = new ProfileController( mockEnvironment );

        //when
        String profile = controller.profile();

        //then
        assertThat( profile ).isEqualTo( expectedProfile );
    }

    @Test
    public void real_profile이_없으면_첫_번째가_조회된다() {
        //given
        String expectedProfile = "oauth";
        MockEnvironment mockEnvironment = new MockEnvironment();

        mockEnvironment.addActiveProfile( expectedProfile );
        mockEnvironment.addActiveProfile( "real-do" );

        ProfileController controller = new ProfileController( mockEnvironment );

        //when
        String profile = controller.profile();

        //then
        assertThat( profile ).isEqualTo( expectedProfile );
    }

    @Test
    public void active_profile이_없으면_default가_조회된다() {
        //given
        String expectedProfile = "default";
        MockEnvironment mockEnvironment = new MockEnvironment();
        ProfileController controller = new ProfileController( mockEnvironment );

        //when
        String profile = controller.profile();

        //then
        assertThat( profile ).isEqualTo( expectedProfile );
    }
}
