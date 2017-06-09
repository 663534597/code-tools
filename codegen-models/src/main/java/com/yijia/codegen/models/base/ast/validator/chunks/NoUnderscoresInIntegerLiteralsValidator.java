package com.yijia.codegen.models.base.ast.validator.chunks;

import com.yijia.codegen.models.base.ast.expr.IntegerLiteralExpr;
import com.yijia.codegen.models.base.ast.expr.LiteralStringValueExpr;
import com.yijia.codegen.models.base.ast.expr.LongLiteralExpr;
import com.yijia.codegen.models.base.ast.validator.ProblemReporter;
import com.yijia.codegen.models.base.ast.validator.VisitorValidator;

public class NoUnderscoresInIntegerLiteralsValidator extends VisitorValidator {
    @Override
    public void visit(IntegerLiteralExpr n, ProblemReporter arg) {
        validate(n, arg);
        super.visit(n, arg);
    }

    @Override
    public void visit(LongLiteralExpr n, ProblemReporter arg) {
        validate(n, arg);
        super.visit(n, arg);
    }

    private static void validate(LiteralStringValueExpr n, ProblemReporter arg) {
        if (n.getValue().contains("_")) {
            arg.report(n, "Underscores in literal values are not supported.");
        }
    }
}
