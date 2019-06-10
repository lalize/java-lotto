package lotto.domain.lottoresult;

import lotto.domain.Rank;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottonumber.LottoNumberPool;
import lotto.domain.lottoticket.LottoTicket;
import lotto.domain.lottoticket.LottoTickets;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

class LottoStatisticsTest {
    private LottoStatistics lottoStatistics;

    @BeforeEach
    void setUp() {
        List<LottoNumber> lottoNumbers1 = Arrays.asList(LottoNumberPool.valueOf(1), LottoNumberPool.valueOf(2)
                , LottoNumberPool.valueOf(3), LottoNumberPool.valueOf(4), LottoNumberPool.valueOf(5)
                , LottoNumberPool.valueOf(6));
        List<LottoNumber> lottoNumbers2 = Arrays.asList(LottoNumberPool.valueOf(7), LottoNumberPool.valueOf(8)
                , LottoNumberPool.valueOf(9), LottoNumberPool.valueOf(10), LottoNumberPool.valueOf(11)
                , LottoNumberPool.valueOf(12));
        List<LottoNumber> lottoNumbers3 = Arrays.asList(LottoNumberPool.valueOf(13), LottoNumberPool.valueOf(14)
                , LottoNumberPool.valueOf(15), LottoNumberPool.valueOf(16), LottoNumberPool.valueOf(17)
                , LottoNumberPool.valueOf(18));
        List<LottoTicket> tickets = Arrays.asList(new LottoTicket(lottoNumbers1)
                , new LottoTicket(lottoNumbers2), new LottoTicket(lottoNumbers3));

        List<LottoNumber> winningTicketNumbers = Arrays.asList(LottoNumberPool.valueOf(1), LottoNumberPool.valueOf(2)
                , LottoNumberPool.valueOf(3), LottoNumberPool.valueOf(10), LottoNumberPool.valueOf(11)
                , LottoNumberPool.valueOf(12));
        LottoTicket winningTicket = new LottoTicket(winningTicketNumbers);
        LottoNumber bonusBall = LottoNumberPool.valueOf(4);
        WinningLotto winningLotto = WinningLotto.of(winningTicket, bonusBall);

        lottoStatistics = LottoStatistics.of(new LottoTickets(tickets), winningLotto);
    }

    @Test
    void LottoResult_getter_체크() {
        assertThat(lottoStatistics.getCountsBy(Rank.FIFTH)).isEqualTo(2);
    }

    @Test
    void 수익률_계산하기() {
        assertThat(lottoStatistics.getProfitRatio()).isEqualTo(3.333, offset(0.00099));
    }

    @AfterEach
    void tearDown() {
        lottoStatistics = null;
    }
}