import is.programmingTechnologies.*;
import is.programmingTechnologies.Accounts.CreditAccount;
import is.programmingTechnologies.Accounts.DebitAccount;
import is.programmingTechnologies.Accounts.DepositAccount;
import is.programmingTechnologies.Entities.Bank;
import is.programmingTechnologies.Interfaces.IAccount;
import is.programmingTechnologies.Interfaces.ITimeManager;
import is.programmingTechnologies.Models.Client;
import is.programmingTechnologies.Services.CentralBank;
import is.programmingTechnologies.Services.TestTimeManager;
import is.programmingTechnologies.Servlet.DTO.BankDTO;
import is.programmingTechnologies.Servlet.DTO.ConfigDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;

public class BanksTest {
    private CentralBank  centralBank;
    private ITimeManager timeMachine;
    private IAccount deposit;
    private IAccount debit;
    private IAccount credit;
    private BankDTO dto1;
    private ConfigDTO configDto;
    private BankDTO dto2;
    private ConfigDTO configDto2;

    public BanksTest() {
    }
    public boolean isMore(BigDecimal first, BigDecimal second) {
        return first.compareTo(second) > 0;
    }
    @Test
    public void IsTimeManagerWorks() throws Exception {
        centralBank = new CentralBank();
        timeMachine = new TestTimeManager(centralBank);
        var lowerBeyond = new ArrayList<BigDecimal>();
        var percents = new ArrayList<BigDecimal>();
        lowerBeyond.add(new BigDecimal(0));
        lowerBeyond.add(new BigDecimal(1000));
        lowerBeyond.add(new BigDecimal(50000));
        percents.add(new BigDecimal(2));
        percents.add(new BigDecimal(4));
        percents.add(new BigDecimal(10));
        Client client = Client.builder().withName("ivj").withSurname("slck").withAddress("sldks").withPassport("dcsnsofino").build();
        Client client1 = Client.builder().withName("wfoeije").withSurname("epomeqp").build();
        var deposit = new DepositAccount();
        var debit = new DebitAccount();
        var credit = new CreditAccount();
        LocalDate futureDate = LocalDate.now();
        futureDate = futureDate.plusYears(Integer.parseInt("2"));
        futureDate = futureDate.plusMonths(Integer.parseInt("3"));
        futureDate = futureDate.plusDays(Integer.parseInt("4"));
        
        var dto1 = new BankDTO();
        var configDto = new ConfigDTO();
        dto1.setСonfigDTO(configDto);
        dto1.setBankName("Sber");
        dto1.getСonfigDTO().setDebitPercent(new BigDecimal(12));
        dto1.getСonfigDTO().setLowerBounds(lowerBeyond);
        dto1.getСonfigDTO().setPercents(percents);
        dto1.getСonfigDTO().setCreditPercent( new BigDecimal(5));
        dto1.getСonfigDTO().setRestrictionDuration(Period.between(LocalDate.now(), futureDate));
        dto1.getСonfigDTO().setTransactionRestrictionLimit(new BigDecimal(7000));
        dto1.getСonfigDTO().setCommissionForTransactions(new BigDecimal(0.01));

        var dto2 = new BankDTO();
        var configDto2 = new ConfigDTO();
        dto2.setСonfigDTO(configDto2);
        dto2.setBankName("Tink");
        dto2.getСonfigDTO().setDebitPercent(new BigDecimal(10));
        dto2.getСonfigDTO().setLowerBounds(lowerBeyond);
        dto2.getСonfigDTO().setPercents(percents);
        dto2.getСonfigDTO().setCreditPercent( new BigDecimal(3));
        dto2.getСonfigDTO().setRestrictionDuration(Period.between(LocalDate.now(), futureDate));
        dto2.getСonfigDTO().setTransactionRestrictionLimit(new BigDecimal(9000));
        dto2.getСonfigDTO().setCommissionForTransactions(new BigDecimal(0.04));

        Bank tink = centralBank.addBank(dto2);
        Bank sber = centralBank.addBank(dto1);

        centralBank.addClient(client, sber.getName(), credit, LocalDateTime.now());
        centralBank.addClient(client1, tink.getName(), debit, LocalDateTime.now());
        timeMachine.increaseTimeFor1Month();
        centralBank.transfer(credit, debit, new BigDecimal(70000));

        Assertions.assertFalse(isMore(credit.getMoney(), new BigDecimal(0)));
        Assertions.assertFalse(isMore(credit.getMoney(), new BigDecimal(0)));
    }

}
