package com.acme.core.ranking.exception;

import com.acme.core.shared.exception.MarvelRankingException;
import com.acme.core.shared.utils.I18nUtils;

public class NoVoteFoundException extends MarvelRankingException {

    public NoVoteFoundException() {
        super(String.format(I18nUtils.getMessage(NoVoteFoundException.class.getSimpleName() + ".message")));
    }
}
