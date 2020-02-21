package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.domain.exception.InvalidLottoNumberException;

public class LottoNumberTest {
	@Test
	@DisplayName("로또 번호 범위가 정상인 경우")
	void of() {
		assertThat(LottoNumber.of(3)).isInstanceOf(LottoNumber.class);
	}

	@ParameterizedTest
	@DisplayName("로또 번호 범위에서 벗어나는 경우")
	@ValueSource(ints = {-1, 0, 46})
	@SuppressWarnings("NonAsciiCharacters")
	void of_범위에서_벗어나는_경우(int actual) {
		assertThatExceptionOfType(InvalidLottoNumberException.class).isThrownBy(() -> LottoNumber.of(actual));
	}
}
