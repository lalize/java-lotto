package lotto.domain;

public interface LottoGenerator {
	Lotto generate();
	Lottos generate(int count);
}