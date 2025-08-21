package edu.isel.csee.jchecker2_0.statics;

import java.io.File;
import java.util.Map;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;

public class ASTChecker {

	private ASTParser parser;
	private String filePath;

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * ASTParser 설정 (소스 + 빌드 옵션)
	 */
	public ASTParser parserSetProperties(String source, String unit, String filePath, String libPath, boolean isBuild) {
		char[] content = source.toCharArray();
		this.filePath = filePath;

		parser = ASTParser.newParser(AST.JLS20);
		parser.setUnitName(unit != null ? unit : "any_name");
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(content);

		// JDK 21 기준 컴파일러 옵션
		Map<String, String> options = JavaCore.getOptions();
		options.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_20);
		options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_21);
		options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_21);

		// 클래스패스 설정 (bin + libPath)
		String[] classpath;
		if (isBuild && libPath != null) {
			classpath = new String[]{filePath + "bin", filePath + libPath};
		} else {
			classpath = new String[]{filePath + "bin"};
		}

		// 소스 경로 설정
		String[] sourcepath = (this.filePath != null && !this.filePath.isEmpty()) ? new String[]{this.filePath} : new String[]{"."};

		// ASTParser 환경 설정
		parser.setEnvironment(classpath, sourcepath, new String[]{"UTF-8"}, true);
		parser.setResolveBindings(true);
		parser.setBindingsRecovery(true);
		parser.setCompilerOptions(options);
		parser.setIgnoreMethodBodies(false);

		return parser;
	}

	/**
	 * ASTParser 설정 (소스만)
	 */
	public ASTParser parserSetProperties(String source) {
		char[] content = source.toCharArray();

		parser = ASTParser.newParser(AST.JLS21);
		parser.setUnitName("any_name");
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(content);

		// JDK 21 기준 컴파일러 옵션
		Map<String, String> options = JavaCore.getOptions();
		options.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_21);
		options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_21);
		options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_21);

		// 클래스패스 + 소스패스
		String[] classpath = new String[]{filePath + "bin"};
		String[] sourcepath = (this.filePath != null && !this.filePath.isEmpty()) ? new String[]{this.filePath} : new String[]{"."};

		parser.setEnvironment(classpath, sourcepath, new String[]{"UTF-8"}, true);
		parser.setResolveBindings(true);
		parser.setBindingsRecovery(true);
		parser.setCompilerOptions(options);
		parser.setIgnoreMethodBodies(false);

		return parser;
	}
}
