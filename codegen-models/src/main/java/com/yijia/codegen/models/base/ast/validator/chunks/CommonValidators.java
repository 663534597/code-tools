package com.yijia.codegen.models.base.ast.validator.chunks;

import com.yijia.codegen.models.base.ast.NodeList;
import com.yijia.codegen.models.base.ast.body.ClassOrInterfaceDeclaration;
import com.yijia.codegen.models.base.ast.body.InitializerDeclaration;
import com.yijia.codegen.models.base.ast.expr.*;
import com.yijia.codegen.models.base.ast.validator.SimpleValidator;
import com.yijia.codegen.models.base.ast.validator.SingleNodeTypeValidator;
import com.yijia.codegen.models.base.ast.validator.TreeVisitorValidator;
import com.yijia.codegen.models.base.ast.validator.Validators;
import com.yijia.codegen.models.base.metamodel.NodeMetaModel;
import com.yijia.codegen.models.base.metamodel.PropertyMetaModel;

import java.util.Optional;

/**
 * Contains validations that are valid for every Java version.
 */
public class CommonValidators extends Validators {
    public CommonValidators() {
        super(
                new SimpleValidator<>(ClassOrInterfaceDeclaration.class,
                        n -> !n.isInterface() && n.getExtendedTypes().size() > 1,
                        (n, reporter) -> reporter.report(n.getExtendedTypes(1), "A class cannot extend more than one other class.")
                ),
                new SimpleValidator<>(ClassOrInterfaceDeclaration.class,
                        n -> n.isInterface() && !n.getImplementedTypes().isEmpty(),
                        (n, reporter) -> reporter.report(n.getImplementedTypes(0), "An interface cannot implement other interfaces.")
                ),
                new SingleNodeTypeValidator<>(ClassOrInterfaceDeclaration.class, (n, reporter) -> {
                    if (n.isInterface()) {
                        n.getMembers().forEach(mem -> {
                            if (mem instanceof InitializerDeclaration) {
                                reporter.report(mem, "An interface cannot have initializers.");
                            }
                        });
                    }
                }
                ),
                new SingleNodeTypeValidator<>(AssignExpr.class, (n, reporter) -> {
                    // https://docs.oracle.com/javase/specs/jls/se8/html/jls-15.html#jls-15.26
                    Expression target = n.getTarget();
                    while (target instanceof EnclosedExpr) {
                        Optional<Expression> inner = ((EnclosedExpr) target).getInner();
                        if (!inner.isPresent()) {
                            reporter.report(n.getTarget(), "Illegal left hand side of an assignment.");
                            return;
                        }
                        target = inner.get();
                    }
                    if (target instanceof NameExpr
                            || target instanceof ArrayAccessExpr
                            || target instanceof FieldAccessExpr) {
                        return;
                    }
                    reporter.report(n.getTarget(), "Illegal left hand side of an assignment.");
                }
                ),
                new TreeVisitorValidator((node, problemReporter) -> {
                    NodeMetaModel mm = node.getMetaModel();
                    for (PropertyMetaModel ppm : mm.getAllPropertyMetaModels()) {
                        if (ppm.isNonEmpty()) {
                            if (ppm.isNodeList()) {
                                NodeList value = (NodeList) ppm.getValue(node);
                                if (value.isEmpty()) {
                                    problemReporter.report(node, "%s.%s can not be empty.", mm.getTypeName(), ppm.getName());
                                }
                            }
                            // No need to check empty strings, it should be impossible to set them to ""
                        }
                    }
                })
        );
    }
}
