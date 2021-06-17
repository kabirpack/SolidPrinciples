package BankATM.Bank.Interface;

public interface ICardDetails {
    void setCardType();
    String getCardType();
    long generateCardNumber();
    String generateValidFrom();
    String generateValidTo();
    int generateCVV();
    int generateSecretPin();
}
