package com.yijia.codegen.models.generator.core;

import com.yijia.codegen.models.generator.core.node.*;
import com.yijia.codegen.models.generator.core.visitor.*;
import com.yijia.codegen.models.base.printer.PrettyPrinter;
import com.yijia.codegen.models.base.printer.PrettyPrinterConfiguration;
import com.yijia.codegen.models.base.utils.SourceRoot;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Generates all generated visitors in the javaparser-core module.
 */
public class CoreGenerator {
	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			throw new RuntimeException("Need 1 parameter: the JavaParser source checkout root directory.");
		}
		final Path root = Paths.get(args[0], "..", "javaparser-core", "src", "main", "java");
		final SourceRoot sourceRoot = new SourceRoot(root);
		sourceRoot.setPrinter(new PrettyPrinter(new PrettyPrinterConfiguration().setEndOfLineCharacter("\n"))::print);

		new CoreGenerator().run(sourceRoot);

		sourceRoot.saveAll();
	}

	private void run(SourceRoot sourceRoot) throws Exception {
		new GenericListVisitorAdapterGenerator(sourceRoot).generate();
		new GenericVisitorAdapterGenerator(sourceRoot).generate();
		new EqualsVisitorGenerator(sourceRoot).generate();
		new VoidVisitorAdapterGenerator(sourceRoot).generate();
		new VoidVisitorGenerator(sourceRoot).generate();
		new GenericVisitorGenerator(sourceRoot).generate();
		new HashCodeVisitorGenerator(sourceRoot).generate();
		new CloneVisitorGenerator(sourceRoot).generate();
		new ModifierVisitorGenerator(sourceRoot).generate();

		new GetNodeListsGenerator(sourceRoot).generate();
		new PropertyGenerator(sourceRoot).generate();
		new RemoveMethodGenerator(sourceRoot).generate();
		new CloneGenerator(sourceRoot).generate();
		new GetMetaModelGenerator(sourceRoot).generate();
		new MainConstructorGenerator(sourceRoot).generate();
	}
}
