package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoStoreTest {
	@Test
	@DisplayName("로또 수동 구매 테스트")
	void buyManual() {
		LottoPurchaseMoney lottoPurchaseMoney = new LottoPurchaseMoney("4000");
		LottoBuyCount lottoBuyCount = lottoPurchaseMoney.getBuyCount("4");
		List<String> manualLottos = new ArrayList<>(Arrays.asList(
				"1,2,3,4,5,6",
				"2,3,4,5,6,7",
				"3,4,5,6,7,8",
				"4,5,6,7,8,9"
		));
		assertThat(LottoStore.buyManualAndAuto(lottoBuyCount, manualLottos).getSize()).isEqualTo(4);
	}

	@Test
	@DisplayName("로또 자동 구매 테스트")
	void buyAuto() {
		LottoPurchaseMoney lottoPurchaseMoney = new LottoPurchaseMoney("5000");
		LottoBuyCount lottoBuyCount = lottoPurchaseMoney.getBuyCount("0");
		assertThat(LottoStore.buyManualAndAuto(lottoBuyCount, Collections.EMPTY_LIST).getSize()).isEqualTo(5);
	}

	@Test
	@DisplayName("로또 수동 + 자동 구매 테스트")
	void buyManualAndAuto() {
		LottoPurchaseMoney lottoPurchaseMoney = new LottoPurchaseMoney("5000");
		LottoBuyCount lottoBuyCount = lottoPurchaseMoney.getBuyCount("2");
		List<String> manualLottos = new ArrayList<>(Arrays.asList("1,2,3,4,5,6", "2,3,4,5,6,7"));
		assertThat(LottoStore.buyManualAndAuto(lottoBuyCount, manualLottos).getSize()).isEqualTo(5);
	}

	@Test
	@DisplayName("당첨 로또 생성 테스트")
	void createWinningLotto() {
		WinningLotto actual = LottoStore.createWinningLotto("1,2,3,4,5,6", "7");
		WinningLotto expected = new WinningLotto(LottoFactory.create("1,2,3,4,5,6"), LottoNumber.of(7));
		assertThat(actual).isEqualTo(expected);
	}
}
