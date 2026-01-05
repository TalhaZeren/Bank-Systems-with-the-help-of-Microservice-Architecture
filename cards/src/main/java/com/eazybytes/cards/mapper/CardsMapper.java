package com.eazybytes.cards.mapper;

import com.eazybytes.cards.dto.CardsDto;
import com.eazybytes.cards.entity.Cards;

public class CardsMapper {


    public static CardsDto mapsToCardsDto(Cards cards,CardsDto cardsDto) {
        cardsDto.setCardNumber( cards.getCardNumber());
        cardsDto.setCardType(cards.getCardType());
        cardsDto.setMobileNumber( cards.getMobileNumber());
        cardsDto.setTotalLimit( cards.getTotalLimit());
        cardsDto.setAmountUsed( cards.getAmountUsed());
        cardsDto.setAvailableAmount( cards.getAvailableAmount());
        return cardsDto;
    }

    public static Cards mapsToCards(CardsDto cardsDto,Cards cards) {
        cards.setCardNumber( cardsDto.getCardNumber());
        cards.setCardType(cardsDto.getCardType());
        cards.setMobileNumber(cardsDto.getMobileNumber());
        cards.setTotalLimit(cardsDto.getTotalLimit());
        cards.setAmountUsed(cardsDto.getAmountUsed());
        cards.setAvailableAmount(cardsDto.getAvailableAmount());
        return cards;
    }
}
