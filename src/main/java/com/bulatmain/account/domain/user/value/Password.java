package com.bulatmain.account.domain.user.value;

import com.bulatmain.account.domain.common.value.StringObject;

public interface Password extends StringObject {
    boolean sameAs(String raw);
}
