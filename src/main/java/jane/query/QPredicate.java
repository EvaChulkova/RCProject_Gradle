package jane.query;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QPredicate {
    private final List<Predicate> predicates = new ArrayList<>();

    public static QPredicate builder() {
        return new QPredicate();
    }

    public<T> QPredicate add(T object, Function<T, Predicate> function) {
        if (object != null) {
            predicates.add(function.apply(object));
        }
        return this;
    }

    /**Создает один predicate из списка, созданный через логическое И*/
    public Predicate buildAnd() {
        return ExpressionUtils.allOf(predicates);
    }

    /**Создает один predicate из списка, созданный через логическое ИЛИ*/
    public Predicate buildOr() {
        return ExpressionUtils.anyOf(predicates);
    }
}
