package com.acme.core.shared.exception;

import com.acme.core.shared.utils.I18nUtils;

public class ProcessingErrorException extends MarvelRankingException {
    public ProcessingErrorException() {
        super(I18nUtils.getMessage(ProcessingErrorException.class.getSimpleName() + ".message"));
    }
}
