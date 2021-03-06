package lotto;

import lotto.domain.LottoBuyCount;
import lotto.domain.LottoPurchaseMoney;
import lotto.domain.LottoStatistics;
import lotto.domain.LottoStore;
import lotto.domain.Lottos;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
	public static void main(String[] args) {
		LottoPurchaseMoney lottoPurchaseMoney = new LottoPurchaseMoney(InputView.inputPurchaseMoney());

		LottoBuyCount lottoBuyCount = lottoPurchaseMoney.getBuyCount(InputView.inputManualLottoCount());
		Lottos lottos = LottoStore.buyManualAndAuto(lottoBuyCount,
				InputView.inputManualLotto(lottoBuyCount.getManual()));
		OutputView.printBuyLottos(lottoBuyCount.getManual(), lottoBuyCount.getAuto(), lottos);

		WinningLotto winningLotto = LottoStore.createWinningLotto(InputView.inputWinningLotto(),
				InputView.inputWinningLottoBonus());

		LottoStatistics lottoStatistics = new LottoStatistics(lottoPurchaseMoney, lottos.match(winningLotto));
		OutputView.printStatistics(lottoStatistics);
	}
}
