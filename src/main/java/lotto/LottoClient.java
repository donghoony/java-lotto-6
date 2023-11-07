package lotto;

import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.LottoBall;
import lotto.domain.LottoReceipt;
import lotto.domain.ProfitCalculator;
import lotto.domain.Rank;
import lotto.domain.WinningNumbers;
import lotto.io.LottoInput;
import lotto.io.LottoOutput;
import lotto.service.PurchaseService;

public class LottoClient {
    private final PurchaseService purchaseService;
    private final LottoInput lottoInput;
    private final LottoOutput lottoOutput;
    private final ProfitCalculator profitCalculator;

    public LottoClient(PurchaseService purchaseService,
                       LottoInput lottoInput,
                       LottoOutput lottoOutput,
                       ProfitCalculator profitCalculator) {
        this.purchaseService = purchaseService;
        this.lottoInput = lottoInput;
        this.lottoOutput = lottoOutput;
        this.profitCalculator = profitCalculator;
    }

    public void startLottery() {
        lottoOutput.printAskingMoney();
        int money = lottoInput.getMoneyAmount();

        LottoReceipt lottoReceipt = purchaseService.purchaseLotto(money);
        lottoOutput.printLottoReceipt(lottoReceipt);

        lottoOutput.printAskingWinningNumbers();
        Lotto winningLotto = lottoInput.getLotto();

        lottoOutput.printAskingBonusNumber();
        LottoBall bonusBall = lottoInput.getBall();

        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusBall);
        Map<Rank, Integer> results = lottoReceipt.getResults(winningNumbers);
        lottoOutput.printResults(results);

        double profitRateInPercentage = profitCalculator.calculateProfitRateInPercentage(results);
        lottoOutput.printProfitAsPercentage(profitRateInPercentage);
    }
}
