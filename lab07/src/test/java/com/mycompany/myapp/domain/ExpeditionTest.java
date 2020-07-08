package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ExpeditionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Expedition.class);
        Expedition expedition1 = new Expedition();
        expedition1.setId(1L);
        Expedition expedition2 = new Expedition();
        expedition2.setId(expedition1.getId());
        assertThat(expedition1).isEqualTo(expedition2);
        expedition2.setId(2L);
        assertThat(expedition1).isNotEqualTo(expedition2);
        expedition1.setId(null);
        assertThat(expedition1).isNotEqualTo(expedition2);
    }
}
