package com.bulatmain.account.application.model.value.fabric;

import com.bulatmain.account.application.model.value.exception.InvalidNameException;
import com.bulatmain.account.application.model.value.validator.StringValidator;
import com.bulatmain.account.domain.user.value.detail.Name;
import lombok.AllArgsConstructor;

import java.util.Scanner;

@AllArgsConstructor
public class NameFabric implements StringObjectFabric<Name> {
    private final StringValidator validator;
    @Override
    public Name build(String record) throws InvalidNameException {
        if (!validator.check(record)) {
            throw new InvalidNameException(String.format(
                    "Error: name record doesn't satisfy format: %s",
                    validator.format()
            ));
        }
        return NameImpl.fromFullName(record);
    }

    @AllArgsConstructor
    private static class NameImpl implements Name {
        private final String name;
        private final String surname;

        protected static NameImpl fromFullName(String fullName) {
            var pair = splitFullName(fullName);
            return new NameImpl(pair.first, pair.second);
        }

        @Override
        public String get() {
            return name + " " + surname;
        }

        private static Pair<String, String> splitFullName(String fullName) {
            Scanner scanner = new Scanner(fullName);
            return new Pair<String, String>(
                    scanner.next("\\w"),
                    scanner.next("\\w")
            );
        }

        private static record Pair<T, U>(T first, U second) {}
    }
}
