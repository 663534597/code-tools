package com.yijia.codegen.models.base.ast.validator.chunks;

import com.yijia.codegen.models.base.ast.Node;
import com.yijia.codegen.models.base.ast.expr.Name;
import com.yijia.codegen.models.base.ast.expr.SimpleName;
import com.yijia.codegen.models.base.ast.validator.ProblemReporter;
import com.yijia.codegen.models.base.ast.validator.VisitorValidator;

public class UnderscoreKeywordValidator extends VisitorValidator {
    @Override
    public void visit(Name n, ProblemReporter arg) {
        validateIdentifier(n, n.getIdentifier(), arg);
        super.visit(n, arg);
    }

    @Override
    public void visit(SimpleName n, ProblemReporter arg) {
        validateIdentifier(n, n.getIdentifier(), arg);
        super.visit(n, arg);
    }

    private static void validateIdentifier(Node n, String id, ProblemReporter arg) {
        if (id.equals("_")) {
            arg.report(n, "'_' is a reserved keyword.");
        }
    }
}
