package nl.geodienstencentrum.maven.plugin.closure.compiler;

import static com.google.javascript.jscomp.CompilerOptions.*;
import static com.google.javascript.jscomp.SourceMap.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Multimap;
import com.google.javascript.jscomp.AnonymousFunctionNamingPolicy;
import com.google.javascript.jscomp.CheckEventfulObjectDisposal;
import com.google.javascript.jscomp.CheckLevel;
import com.google.javascript.jscomp.CodingConvention;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.CompilerPass;
import com.google.javascript.jscomp.ComposeWarningsGuard;
import com.google.javascript.jscomp.CssRenamingMap;
import com.google.javascript.jscomp.CustomPassExecutionTime;
import com.google.javascript.jscomp.DependencyOptions;
import com.google.javascript.jscomp.DiagnosticGroup;
import com.google.javascript.jscomp.ErrorFormat;
import com.google.javascript.jscomp.ErrorHandler;
import com.google.javascript.jscomp.MessageBundle;
import com.google.javascript.jscomp.PropertyRenamingPolicy;
import com.google.javascript.jscomp.RenamingMap;
import com.google.javascript.jscomp.VariableMap;
import com.google.javascript.jscomp.VariableRenamingPolicy;
import com.google.javascript.jscomp.WarningsGuard;

/**
 * options for a compilation. This is a wrapper around a
 * {@link com.google.javascript.jscomp.CompilerOptions} .
 *
 *
 * @author mprins
 *
 * @see com.google.javascript.jscomp.CompilerOptions
 */
public class CompilerOptionsMojo {

    /**
     * wrapped CompilerOptions.
     */
    private final CompilerOptions compilerOptions = new CompilerOptions();

    public void setAggressiveVarCheck(CheckLevel level) {
        compilerOptions.setAggressiveVarCheck(level);
    }

    public void setReportMissingOverride(CheckLevel level) {
        compilerOptions.setReportMissingOverride(level);
    }

    public void setCheckRequires(CheckLevel level) {
        compilerOptions.setCheckRequires(level);
    }

    public void setCheckProvides(CheckLevel level) {
        compilerOptions.setCheckProvides(level);
    }

    public void setCheckGlobalNamesLevel(CheckLevel level) {
        compilerOptions.setCheckGlobalNamesLevel(level);
    }

    public void setBrokenClosureRequiresLevel(CheckLevel level) {
        compilerOptions.setBrokenClosureRequiresLevel(level);
    }

    public void setCheckGlobalThisLevel(CheckLevel level) {
        compilerOptions.setCheckGlobalThisLevel(level);
    }

    public void setCheckMissingGetCssNameLevel(CheckLevel level) {
        compilerOptions.setCheckMissingGetCssNameLevel(level);
    }

    public void setCheckEventfulObjectDisposalPolicy(CheckEventfulObjectDisposal.DisposalCheckingPolicy policy) {
        compilerOptions.setCheckEventfulObjectDisposalPolicy(policy);
    }

    public void setCheckMissingReturn(CheckLevel level) {
        compilerOptions.setCheckMissingReturn(level);
    }

    public void setAliasableGlobals(String names) {
        compilerOptions.setAliasableGlobals(names);
    }

    public void setUnaliasableGlobals(String names) {
        compilerOptions.setUnaliasableGlobals(names);
    }

    public void setCollapseObjectLiterals(boolean enabled) {
        compilerOptions.setCollapseObjectLiterals(enabled);
    }

    public void setSpecializeInitialModule(boolean enabled) {
        compilerOptions.setSpecializeInitialModule(enabled);
    }

    public void setReplaceMessagesWithChromeI18n(boolean replaceMessagesWithChromeI18n, String tcProjectId) {
        compilerOptions.setReplaceMessagesWithChromeI18n(replaceMessagesWithChromeI18n, tcProjectId);
    }

    public void setAppNameStr(String appNameStr) {
        compilerOptions.setAppNameStr(appNameStr);
    }

    public void setPreferSingleQuotes(boolean enabled) {
        compilerOptions.setPreferSingleQuotes(enabled);
    }

    public void setTrustedStrings(boolean yes) {
        compilerOptions.setTrustedStrings(yes);
    }

    public void setReportPath(String reportPath) {
        compilerOptions.setReportPath(reportPath);
    }

    public void setTracerMode(TracerMode mode) {
        compilerOptions.setTracerMode(mode);
    }

    public void setNameReferenceReportPath(String filePath) {
        compilerOptions.setNameReferenceReportPath(filePath);
    }

    public void setNameReferenceGraphPath(String filePath) {
        compilerOptions.setNameReferenceGraphPath(filePath);
    }

    public void setProtectHiddenSideEffects(boolean enable) {
        compilerOptions.setProtectHiddenSideEffects(enable);
    }

    public void setRemoveUnusedClassProperties(boolean removeUnusedClassProperties) {
        compilerOptions.setRemoveUnusedClassProperties(removeUnusedClassProperties);
    }

    public void setDefineToBooleanLiteral(String defineName, boolean value) {
        compilerOptions.setDefineToBooleanLiteral(defineName, value);
    }

    public void setDefineToStringLiteral(String defineName, String value) {
        compilerOptions.setDefineToStringLiteral(defineName, value);
    }

    public void setDefineToNumberLiteral(String defineName, int value) {
        compilerOptions.setDefineToNumberLiteral(defineName, value);
    }

    public void setDefineToDoubleLiteral(String defineName, double value) {
        compilerOptions.setDefineToDoubleLiteral(defineName, value);
    }

    public void setTweakToBooleanLiteral(String tweakId, boolean value) {
        compilerOptions.setTweakToBooleanLiteral(tweakId, value);
    }

    public void setTweakToStringLiteral(String tweakId, String value) {
        compilerOptions.setTweakToStringLiteral(tweakId, value);
    }

    public void setTweakToNumberLiteral(String tweakId, int value) {
        compilerOptions.setTweakToNumberLiteral(tweakId, value);
    }

    public void setTweakToDoubleLiteral(String tweakId, double value) {
        compilerOptions.setTweakToDoubleLiteral(tweakId, value);
    }

    public void setWarningLevel(DiagnosticGroup type, CheckLevel level) {
        compilerOptions.setWarningLevel(type, level);
    }

    public void addWarningsGuard(WarningsGuard guard) {
        compilerOptions.addWarningsGuard(guard);
    }

    public void setRenamingPolicy(VariableRenamingPolicy newVariablePolicy, PropertyRenamingPolicy newPropertyPolicy) {
        compilerOptions.setRenamingPolicy(newVariablePolicy, newPropertyPolicy);
    }

    public void setPropertyAffinity(boolean useAffinity) {
        compilerOptions.setPropertyAffinity(useAffinity);
    }

    public void setShadowVariables(boolean shadow) {
        compilerOptions.setShadowVariables(shadow);
    }

    public void setCollapsePropertiesOnExternTypes(boolean collapse) {
        compilerOptions.setCollapsePropertiesOnExternTypes(collapse);
    }

    public void setProcessObjectPropertyString(boolean process) {
        compilerOptions.setProcessObjectPropertyString(process);
    }

    public void setReplaceIdGenerators(boolean replaceIdGenerators) {
        compilerOptions.setReplaceIdGenerators(replaceIdGenerators);
    }

    public void setIdGenerators(Set<String> idGenerators) {
        compilerOptions.setIdGenerators(idGenerators);
    }

    public void setIdGenerators(Map<String, RenamingMap> idGenerators) {
        compilerOptions.setIdGenerators(idGenerators);
    }

    public void setIdGeneratorsMap(String previousMappings) {
        compilerOptions.setIdGeneratorsMap(previousMappings);
    }

    public void setInlineFunctions(Reach reach) {
        compilerOptions.setInlineFunctions(reach);
    }

    public void setInlineVariables(Reach reach) {
        compilerOptions.setInlineVariables(reach);
    }

    public void setInlineProperties(boolean enable) {
        compilerOptions.setInlineProperties(enable);
    }

    public void setRemoveUnusedVariables(Reach reach) {
        compilerOptions.setRemoveUnusedVariables(reach);
    }

    public void setReplaceStringsConfiguration(String placeholderToken, List<String> functionDescriptors) {
        compilerOptions.setReplaceStringsConfiguration(placeholderToken, functionDescriptors);
    }

    public void setRemoveAbstractMethods(boolean remove) {
        compilerOptions.setRemoveAbstractMethods(remove);
    }

    public void setRemoveClosureAsserts(boolean remove) {
        compilerOptions.setRemoveClosureAsserts(remove);
    }

    public void setNameAnonymousFunctionsOnly(boolean value) {
        compilerOptions.setNameAnonymousFunctionsOnly(value);
    }

    public void setColorizeErrorOutput(boolean colorizeErrorOutput) {
        compilerOptions.setColorizeErrorOutput(colorizeErrorOutput);
    }

    public void setChainCalls(boolean value) {
        compilerOptions.setChainCalls(value);
    }

    public void setAcceptConstKeyword(boolean value) {
        compilerOptions.setAcceptConstKeyword(value);
    }

    public void enableRuntimeTypeCheck(String logFunction) {
        compilerOptions.enableRuntimeTypeCheck(logFunction);
    }

    public void setGenerateExports(boolean generateExports) {
        compilerOptions.setGenerateExports(generateExports);
    }

    public void setExportLocalPropertyDefinitions(boolean export) {
        compilerOptions.setExportLocalPropertyDefinitions(export);
    }

    public void setAngularPass(boolean angularPass) {
        compilerOptions.setAngularPass(angularPass);
    }

    public void setCodingConvention(CodingConvention codingConvention) {
        compilerOptions.setCodingConvention(codingConvention);
    }

    public void setDependencyOptions(DependencyOptions options) {
        compilerOptions.setDependencyOptions(options);
    }

    public void setManageClosureDependencies(boolean newVal) {
        compilerOptions.setManageClosureDependencies(newVal);
    }

    public void setManageClosureDependencies(List<String> entryPoints) {
        compilerOptions.setManageClosureDependencies(entryPoints);
    }

    public void setSummaryDetailLevel(int summaryDetailLevel) {
        compilerOptions.setSummaryDetailLevel(summaryDetailLevel);
    }

    public void enableExternExports(boolean enabled) {
        compilerOptions.enableExternExports(enabled);
    }

    public void setExtraAnnotationNames(Iterable<String> extraAnnotationNames) {
        compilerOptions.setExtraAnnotationNames(extraAnnotationNames);
    }

    public void setOutputCharset(String charsetName) {
        compilerOptions.setOutputCharset(charsetName);
    }

    public void setTweakProcessing(TweakProcessing tweakProcessing) {
        compilerOptions.setTweakProcessing(tweakProcessing);
    }

    public void setLanguageIn(LanguageMode languageIn) {
        compilerOptions.setLanguageIn(languageIn);
    }

    public void setLooseTypes(boolean looseTypes) {
        compilerOptions.setLooseTypes(looseTypes);
    }

    public void setAliasTransformationHandler(AliasTransformationHandler changes) {
        compilerOptions.setAliasTransformationHandler(changes);
    }

    public void setErrorHandler(ErrorHandler handler) {
        compilerOptions.setErrorHandler(handler);
    }

    public void setInferTypes(boolean enable) {
        compilerOptions.setInferTypes(enable);
    }

    public void setNewTypeInference(boolean enable) {
        compilerOptions.setNewTypeInference(enable);
    }

    public void setAssumeStrictThis(boolean enable) {
        compilerOptions.setAssumeStrictThis(enable);
    }

    public void setAssumeClosuresOnlyCaptureReferences(boolean enable) {
        compilerOptions.setAssumeClosuresOnlyCaptureReferences(enable);
    }

    public void setPropertyInvalidationErrors(Map<String, CheckLevel> propertyInvalidationErrors) {
        compilerOptions.setPropertyInvalidationErrors(propertyInvalidationErrors);
    }

    public void setLanguageOut(LanguageMode languageOut) {
        compilerOptions.setLanguageOut(languageOut);
    }

    public void setIdeMode(boolean ideMode) {
        compilerOptions.setIdeMode(ideMode);
    }

    public void setSaveDataStructures(boolean save) {
        compilerOptions.setSaveDataStructures(save);
    }

    public void setSkipAllPasses(boolean skipAllPasses) {
        compilerOptions.setSkipAllPasses(skipAllPasses);
    }

    public void setCheckDeterminism(boolean checkDeterminism) {
        compilerOptions.setCheckDeterminism(checkDeterminism);
    }

    public void setMessageBundle(MessageBundle messageBundle) {
        compilerOptions.setMessageBundle(messageBundle);
    }

    public void setCheckSymbols(boolean checkSymbols) {
        compilerOptions.setCheckSymbols(checkSymbols);
    }

    public void setCheckSuspiciousCode(boolean checkSuspiciousCode) {
        compilerOptions.setCheckSuspiciousCode(checkSuspiciousCode);
    }

    public void setCheckControlStructures(boolean checkControlStructures) {
        compilerOptions.setCheckControlStructures(checkControlStructures);
    }

    public void setCheckTypes(boolean checkTypes) {
        compilerOptions.setCheckTypes(checkTypes);
    }

    public void setCheckMissingGetCssNameBlacklist(String blackList) {
        compilerOptions.setCheckMissingGetCssNameBlacklist(blackList);
    }

    public void setAggressiveRenaming(boolean aggressive) {
        compilerOptions.setAggressiveRenaming(aggressive);
    }

    public void setFoldConstants(boolean foldConstants) {
        compilerOptions.setFoldConstants(foldConstants);
    }

    public void setDeadAssignmentElimination(boolean deadAssignmentElimination) {
        compilerOptions.setDeadAssignmentElimination(deadAssignmentElimination);
    }

    public void setInlineConstantVars(boolean inlineConstantVars) {
        compilerOptions.setInlineConstantVars(inlineConstantVars);
    }

    public void setInlineFunctions(boolean inlineFunctions) {
        compilerOptions.setInlineFunctions(inlineFunctions);
    }

    public void setInlineLocalFunctions(boolean inlineLocalFunctions) {
        compilerOptions.setInlineLocalFunctions(inlineLocalFunctions);
    }

    public void setCrossModuleCodeMotion(boolean crossModuleCodeMotion) {
        compilerOptions.setCrossModuleCodeMotion(crossModuleCodeMotion);
    }

    public void setParentModuleCanSeeSymbolsDeclaredInChildren(boolean parentModuleCanSeeSymbolsDeclaredInChildren) {
        compilerOptions.setParentModuleCanSeeSymbolsDeclaredInChildren(parentModuleCanSeeSymbolsDeclaredInChildren);
    }

    public void setCoalesceVariableNames(boolean coalesceVariableNames) {
        compilerOptions.setCoalesceVariableNames(coalesceVariableNames);
    }

    public void setCrossModuleMethodMotion(boolean crossModuleMethodMotion) {
        compilerOptions.setCrossModuleMethodMotion(crossModuleMethodMotion);
    }

    public void setInlineGetters(boolean inlineGetters) {
        compilerOptions.setInlineGetters(inlineGetters);
    }

    public void setInlineVariables(boolean inlineVariables) {
        compilerOptions.setInlineVariables(inlineVariables);
    }

    public void setInlineLocalVariables(boolean inlineLocalVariables) {
        compilerOptions.setInlineLocalVariables(inlineLocalVariables);
    }

    public void setFlowSensitiveInlineVariables(boolean enabled) {
        compilerOptions.setFlowSensitiveInlineVariables(enabled);
    }

    public void setSmartNameRemoval(boolean smartNameRemoval) {
        compilerOptions.setSmartNameRemoval(smartNameRemoval);
    }

    public void setExtraSmartNameRemoval(boolean smartNameRemoval) {
        compilerOptions.setExtraSmartNameRemoval(smartNameRemoval);
    }

    public void setRemoveDeadCode(boolean removeDeadCode) {
        compilerOptions.setRemoveDeadCode(removeDeadCode);
    }

    public void setExtractPrototypeMemberDeclarations(boolean enabled) {
        compilerOptions.setExtractPrototypeMemberDeclarations(enabled);
    }

    public void setRemoveUnusedPrototypeProperties(boolean enabled) {
        compilerOptions.setRemoveUnusedPrototypeProperties(enabled);
    }

    public void setRemoveUnusedPrototypePropertiesInExterns(boolean enabled) {
        compilerOptions.setRemoveUnusedPrototypePropertiesInExterns(enabled);
    }

    public void setRemoveUnusedVars(boolean removeUnusedVars) {
        compilerOptions.setRemoveUnusedVars(removeUnusedVars);
    }

    public void setRemoveUnusedLocalVars(boolean removeUnusedLocalVars) {
        compilerOptions.setRemoveUnusedLocalVars(removeUnusedLocalVars);
    }

    public void setAliasExternals(boolean aliasExternals) {
        compilerOptions.setAliasExternals(aliasExternals);
    }

    public void setCollapseVariableDeclarations(boolean enabled) {
        compilerOptions.setCollapseVariableDeclarations(enabled);
    }

    public void setGroupVariableDeclarations(boolean enabled) {
        compilerOptions.setGroupVariableDeclarations(enabled);
    }

    public void setCollapseAnonymousFunctions(boolean enabled) {
        compilerOptions.setCollapseAnonymousFunctions(enabled);
    }

    public void setAliasableStrings(Set<String> aliasableStrings) {
        compilerOptions.setAliasableStrings(aliasableStrings);
    }

    public void setAliasStringsBlacklist(String aliasStringsBlacklist) {
        compilerOptions.setAliasStringsBlacklist(aliasStringsBlacklist);
    }

    public void setAliasAllStrings(boolean aliasAllStrings) {
        compilerOptions.setAliasAllStrings(aliasAllStrings);
    }

    public void setOutputJsStringUsage(boolean outputJsStringUsage) {
        compilerOptions.setOutputJsStringUsage(outputJsStringUsage);
    }

    public void setConvertToDottedProperties(boolean convertToDottedProperties) {
        compilerOptions.setConvertToDottedProperties(convertToDottedProperties);
    }

    public void setRewriteFunctionExpressions(boolean rewriteFunctionExpressions) {
        compilerOptions.setRewriteFunctionExpressions(rewriteFunctionExpressions);
    }

    public void setOptimizeParameters(boolean optimizeParameters) {
        compilerOptions.setOptimizeParameters(optimizeParameters);
    }

    public void setOptimizeReturns(boolean optimizeReturns) {
        compilerOptions.setOptimizeReturns(optimizeReturns);
    }

    public void setOptimizeCalls(boolean optimizeCalls) {
        compilerOptions.setOptimizeCalls(optimizeCalls);
    }

    public void setOptimizeArgumentsArray(boolean optimizeArgumentsArray) {
        compilerOptions.setOptimizeArgumentsArray(optimizeArgumentsArray);
    }

    public void setVariableRenaming(VariableRenamingPolicy variableRenaming) {
        compilerOptions.setVariableRenaming(variableRenaming);
    }

    public void setPropertyRenaming(PropertyRenamingPolicy propertyRenaming) {
        compilerOptions.setPropertyRenaming(propertyRenaming);
    }

    public void setLabelRenaming(boolean labelRenaming) {
        compilerOptions.setLabelRenaming(labelRenaming);
    }

    public void setReserveRawExports(boolean reserveRawExports) {
        compilerOptions.setReserveRawExports(reserveRawExports);
    }

    public void setPreferStableNames(boolean preferStableNames) {
        compilerOptions.setPreferStableNames(preferStableNames);
    }

    public void setGeneratePseudoNames(boolean generatePseudoNames) {
        compilerOptions.setGeneratePseudoNames(generatePseudoNames);
    }

    public void setRenamePrefix(String renamePrefix) {
        compilerOptions.setRenamePrefix(renamePrefix);
    }

    public void setRenamePrefixNamespace(String renamePrefixNamespace) {
        compilerOptions.setRenamePrefixNamespace(renamePrefixNamespace);
    }

    public void setAliasKeywords(boolean aliasKeywords) {
        compilerOptions.setAliasKeywords(aliasKeywords);
    }

    public void setCollapseProperties(boolean collapseProperties) {
        compilerOptions.setCollapseProperties(collapseProperties);
    }

    public void setDevirtualizePrototypeMethods(boolean devirtualizePrototypeMethods) {
        compilerOptions.setDevirtualizePrototypeMethods(devirtualizePrototypeMethods);
    }

    public void setComputeFunctionSideEffects(boolean computeFunctionSideEffects) {
        compilerOptions.setComputeFunctionSideEffects(computeFunctionSideEffects);
    }

    public void setDebugFunctionSideEffectsPath(String debugFunctionSideEffectsPath) {
        compilerOptions.setDebugFunctionSideEffectsPath(debugFunctionSideEffectsPath);
    }

    public void setDisambiguatePrivateProperties(boolean value) {
        compilerOptions.setDisambiguatePrivateProperties(value);
    }

    public void setDisambiguateProperties(boolean disambiguateProperties) {
        compilerOptions.setDisambiguateProperties(disambiguateProperties);
    }

    public void setAmbiguateProperties(boolean ambiguateProperties) {
        compilerOptions.setAmbiguateProperties(ambiguateProperties);
    }

    public void setAnonymousFunctionNaming(AnonymousFunctionNamingPolicy anonymousFunctionNaming) {
        compilerOptions.setAnonymousFunctionNaming(anonymousFunctionNaming);
    }

    public void setInputAnonymousFunctionNamingMap(VariableMap inputMap) {
        compilerOptions.setInputAnonymousFunctionNamingMap(inputMap);
    }

    public void setInputVariableMap(VariableMap inputVariableMap) {
        compilerOptions.setInputVariableMap(inputVariableMap);
    }

    public void setInputPropertyMap(VariableMap inputPropertyMap) {
        compilerOptions.setInputPropertyMap(inputPropertyMap);
    }

    public void setExportTestFunctions(boolean exportTestFunctions) {
        compilerOptions.setExportTestFunctions(exportTestFunctions);
    }

    public void setRuntimeTypeCheck(boolean runtimeTypeCheck) {
        compilerOptions.setRuntimeTypeCheck(runtimeTypeCheck);
    }

    public void setRuntimeTypeCheckLogFunction(String runtimeTypeCheckLogFunction) {
        compilerOptions.setRuntimeTypeCheckLogFunction(runtimeTypeCheckLogFunction);
    }

    public void setSyntheticBlockStartMarker(String syntheticBlockStartMarker) {
        compilerOptions.setSyntheticBlockStartMarker(syntheticBlockStartMarker);
    }

    public void setSyntheticBlockEndMarker(String syntheticBlockEndMarker) {
        compilerOptions.setSyntheticBlockEndMarker(syntheticBlockEndMarker);
    }

    public void setLocale(String locale) {
        compilerOptions.setLocale(locale);
    }

    public void setMarkAsCompiled(boolean markAsCompiled) {
        compilerOptions.setMarkAsCompiled(markAsCompiled);
    }

    public void setClosurePass(boolean closurePass) {
        compilerOptions.setClosurePass(closurePass);
    }

    public void setPreserveGoogRequires(boolean preserveGoogRequires) {
        compilerOptions.setPreserveGoogRequires(preserveGoogRequires);
    }

    public void setGatherCssNames(boolean gatherCssNames) {
        compilerOptions.setGatherCssNames(gatherCssNames);
    }

    public void setStripTypes(Set<String> stripTypes) {
        compilerOptions.setStripTypes(stripTypes);
    }

    public void setStripNameSuffixes(Set<String> stripNameSuffixes) {
        compilerOptions.setStripNameSuffixes(stripNameSuffixes);
    }

    public void setStripNamePrefixes(Set<String> stripNamePrefixes) {
        compilerOptions.setStripNamePrefixes(stripNamePrefixes);
    }

    public void setStripTypePrefixes(Set<String> stripTypePrefixes) {
        compilerOptions.setStripTypePrefixes(stripTypePrefixes);
    }

    public void setCustomPasses(Multimap<CustomPassExecutionTime, CompilerPass> customPasses) {
        compilerOptions.setCustomPasses(customPasses);
    }

    public void setMarkNoSideEffectCalls(boolean markNoSideEffectCalls) {
        compilerOptions.setMarkNoSideEffectCalls(markNoSideEffectCalls);
    }

    public void setDefineReplacements(Map<String, Object> defineReplacements) {
        compilerOptions.setDefineReplacements(defineReplacements);
    }

    public void setTweakReplacements(Map<String, Object> tweakReplacements) {
        compilerOptions.setTweakReplacements(tweakReplacements);
    }

    public void setMoveFunctionDeclarations(boolean moveFunctionDeclarations) {
        compilerOptions.setMoveFunctionDeclarations(moveFunctionDeclarations);
    }

    public void setInstrumentationTemplate(String instrumentationTemplate) {
        compilerOptions.setInstrumentationTemplate(instrumentationTemplate);
    }

    public void setRecordFunctionInformation(boolean recordFunctionInformation) {
        compilerOptions.setRecordFunctionInformation(recordFunctionInformation);
    }

    public void setCssRenamingMap(CssRenamingMap cssRenamingMap) {
        compilerOptions.setCssRenamingMap(cssRenamingMap);
    }

    public void setCssRenamingWhitelist(Set<String> whitelist) {
        compilerOptions.setCssRenamingWhitelist(whitelist);
    }

    public void setReplaceStringsFunctionDescriptions(List<String> replaceStringsFunctionDescriptions) {
        compilerOptions.setReplaceStringsFunctionDescriptions(replaceStringsFunctionDescriptions);
    }

    public void setReplaceStringsPlaceholderToken(String replaceStringsPlaceholderToken) {
        compilerOptions.setReplaceStringsPlaceholderToken(replaceStringsPlaceholderToken);
    }

    public void setReplaceStringsReservedStrings(Set<String> replaceStringsReservedStrings) {
        compilerOptions.setReplaceStringsReservedStrings(replaceStringsReservedStrings);
    }

    public void setReplaceStringsInputMap(VariableMap serializedMap) {
        compilerOptions.setReplaceStringsInputMap(serializedMap);
    }

    public void setPrettyPrint(boolean prettyPrint) {
        compilerOptions.setPrettyPrint(prettyPrint);
    }

    public void setLineBreak(boolean lineBreak) {
        compilerOptions.setLineBreak(lineBreak);
    }

    public void setPreferLineBreakAtEndOfFile(boolean lineBreakAtEnd) {
        compilerOptions.setPreferLineBreakAtEndOfFile(lineBreakAtEnd);
    }

    public void setPrintInputDelimiter(boolean printInputDelimiter) {
        compilerOptions.setPrintInputDelimiter(printInputDelimiter);
    }

    public void setInputDelimiter(String inputDelimiter) {
        compilerOptions.setInputDelimiter(inputDelimiter);
    }

    public void setErrorFormat(ErrorFormat errorFormat) {
        compilerOptions.setErrorFormat(errorFormat);
    }

    public void setWarningsGuard(ComposeWarningsGuard warningsGuard) {
        compilerOptions.setWarningsGuard(warningsGuard);
    }

    public void setLineLengthThreshold(int lineLengthThreshold) {
        compilerOptions.setLineLengthThreshold(lineLengthThreshold);
    }

    public void setExternExports(boolean externExports) {
        compilerOptions.setExternExports(externExports);
    }

    public void setExternExportsPath(String externExportsPath) {
        compilerOptions.setExternExportsPath(externExportsPath);
    }

    public void setSourceMapOutputPath(String sourceMapOutputPath) {
        compilerOptions.setSourceMapOutputPath(sourceMapOutputPath);
    }

    public void setSourceMapDetailLevel(DetailLevel sourceMapDetailLevel) {
        compilerOptions.setSourceMapDetailLevel(sourceMapDetailLevel);
    }

    public void setSourceMapFormat(Format sourceMapFormat) {
        compilerOptions.setSourceMapFormat(sourceMapFormat);
    }

    public void setSourceMapLocationMappings(List<LocationMapping> sourceMapLocationMappings) {
        compilerOptions.setSourceMapLocationMappings(sourceMapLocationMappings);
    }

    public void setTransformAMDToCJSModules(boolean transformAMDToCJSModules) {
        compilerOptions.setTransformAMDToCJSModules(transformAMDToCJSModules);
    }

    public void setProcessCommonJSModules(boolean processCommonJSModules) {
        compilerOptions.setProcessCommonJSModules(processCommonJSModules);
    }

    public void setCommonJSModulePathPrefix(String commonJSModulePathPrefix) {
        compilerOptions.setCommonJSModulePathPrefix(commonJSModulePathPrefix);
    }

    public void setInstrumentMemoryAllocations(boolean instrumentMemoryAllocations) {
        compilerOptions.setInstrumentMemoryAllocations(instrumentMemoryAllocations);
    }

    public void setInstrumentForCoverage(boolean instrumentForCoverage) {
        compilerOptions.setInstrumentForCoverage(instrumentForCoverage);
    }

    /**
     * @return string representation of the wrapped
     * {@link com.google.javascript.jscomp.CompilerOptions}.
     *
     * @see java.lang.Object#toString()
     * @see com.google.javascript.jscomp.CompilerOptions#toString()
     */
    @Override
    public String toString() {
        return this.compilerOptions.toString();
    }

    public CompilerOptions getCompilerOptions() {
        return this.compilerOptions;
    }
}
