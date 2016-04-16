/*
 * Copyright 2015-2016 Mark Prins, GeoDienstenCentrum.
 * Copyright 2012-2014 Mason Bryant
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors
 * may be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package nl.geodienstencentrum.maven.plugin.closure.compiler;

import com.google.common.collect.Multimap;
import com.google.javascript.jscomp.AnonymousFunctionNamingPolicy;
import com.google.javascript.jscomp.CheckEventfulObjectDisposal;
import com.google.javascript.jscomp.CheckLevel;
import com.google.javascript.jscomp.CodingConvention;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.CompilerOptions.AliasTransformationHandler;
import com.google.javascript.jscomp.CompilerOptions.Environment;
import com.google.javascript.jscomp.CompilerOptions.LanguageMode;
import com.google.javascript.jscomp.CompilerOptions.Reach;
import com.google.javascript.jscomp.CompilerOptions.TracerMode;
import com.google.javascript.jscomp.CompilerOptions.TweakProcessing;
import com.google.javascript.jscomp.CompilerPass;
import com.google.javascript.jscomp.ComposeWarningsGuard;
import com.google.javascript.jscomp.CssRenamingMap;
import com.google.javascript.jscomp.CustomPassExecutionTime;
import com.google.javascript.jscomp.DependencyOptions;
import com.google.javascript.jscomp.DiagnosticGroup;
import com.google.javascript.jscomp.DiagnosticGroups;
import com.google.javascript.jscomp.ErrorFormat;
import com.google.javascript.jscomp.ErrorHandler;
import com.google.javascript.jscomp.Instrumentation;
import com.google.javascript.jscomp.MessageBundle;
import com.google.javascript.jscomp.PropertyRenamingPolicy;
import com.google.javascript.jscomp.RenamingMap;
import com.google.javascript.jscomp.SourceMap.DetailLevel;
import com.google.javascript.jscomp.SourceMap.Format;
import com.google.javascript.jscomp.SourceMap.LocationMapping;
import com.google.javascript.jscomp.VariableMap;
import com.google.javascript.jscomp.VariableRenamingPolicy;
import com.google.javascript.jscomp.WarningsGuard;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    public void setMaxFunctionSizeAfterInlining(int funAstSize) {
        compilerOptions.setMaxFunctionSizeAfterInlining(funAstSize);
    }

    public void setRemoveUnusedConstructorProperties(boolean removeUnusedConstructorProperties) {
        compilerOptions.setRemoveUnusedConstructorProperties(removeUnusedConstructorProperties);
    }

    public void setPolymerPass(boolean polymerPass) {
        this.compilerOptions.setPolymerPass(polymerPass);
    }

    public void setDartPass(boolean dartPass) {
        this.compilerOptions.setDartPass(dartPass);
    }

    public void setJ2clPass(boolean j2clPass) {
        this.compilerOptions.setJ2clPass(j2clPass);
    }

    public void setChecksOnly(boolean checksOnly) {
        this.compilerOptions.setChecksOnly(checksOnly);
    }

    public void setInstrumentationTemplateFile(String filename) {
        this.compilerOptions.setInstrumentationTemplateFile(filename);
    }

    public void setPreserveTypeAnnotations(boolean preserveTypeAnnotations) {
        this.compilerOptions.setPreserveTypeAnnotations(preserveTypeAnnotations);
    }

    public void setSkipNonTranspilationPasses(boolean skipNonTranspilationPasses) {
        this.compilerOptions.setSkipNonTranspilationPasses(skipNonTranspilationPasses);
    }

    public void setEnvironment(Environment environment) {
        this.compilerOptions.setEnvironment(environment);
    }

    /**
     * @param level ignored
     * @deprecated no longer a compiler option
     */
    @Deprecated
    public void setAggressiveVarCheck(CheckLevel level) {
        // compilerOptions.setAggressiveVarCheck(level);
    }

    public void setReportMissingOverride(CheckLevel level) {
        compilerOptions.setReportMissingOverride(level);
    }

    /**
     * @param level the checklevel
     * @deprecated use
     * {@link #setWarningLevel(com.google.javascript.jscomp.DiagnosticGroup, com.google.javascript.jscomp.CheckLevel)}
     */
    @Deprecated
    public void setCheckRequires(CheckLevel level) {
        this.setWarningLevel(DiagnosticGroups.MISSING_REQUIRE, level);
    }

    /**
     * @param level the diagnostic level
     * @deprecated use
     * {@link #setWarningLevel(com.google.javascript.jscomp.DiagnosticGroup, com.google.javascript.jscomp.CheckLevel)}
     */
    @Deprecated
    public void setCheckProvides(CheckLevel level) {
        this.setWarningLevel(DiagnosticGroups.MISSING_PROVIDE, level);
    }

    public void setCheckGlobalNamesLevel(CheckLevel level) {
        compilerOptions.setCheckGlobalNamesLevel(level);
    }

    /**
     * @param level the checklevel
     * @deprecated use
     * {@link #setWarningLevel(com.google.javascript.jscomp.DiagnosticGroup, com.google.javascript.jscomp.CheckLevel)}
     */
    @Deprecated
    public void setBrokenClosureRequiresLevel(CheckLevel level) {
        this.setWarningLevel(DiagnosticGroups.MISSING_REQUIRE, level);
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

    /**
     * @param level ignored
     * @deprecated no longer a compiler option
     */
    @Deprecated
    public void setCheckMissingReturn(CheckLevel level) {
        //compilerOptions.setCheckMissingReturn(level);
    }

    /**
     * @param names ignored
     * @deprecated no longer a compiler option
     */
    @Deprecated
    public void setAliasableGlobals(String names) {
        //compilerOptions.setAliasableGlobals(names);
    }

    /**
     * @param names ignored
     * @deprecated no longer a compiler option
     */
    @Deprecated
    public void setUnaliasableGlobals(String names) {
        //compilerOptions.setUnaliasableGlobals(names);
    }

    public void setCollapseObjectLiterals(boolean enabled) {
        compilerOptions.setCollapseObjectLiterals(enabled);
    }

    /**
     * @param enabled ignored
     * @deprecated no longer a compiler option
     *
     */
    @Deprecated
    public void setSpecializeInitialModule(boolean enabled) {
        //compilerOptions.setSpecializeInitialModule(enabled);
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

    /**
     * @param useAffinity ignored
     * @deprecated no longer a compiler option
     */
    @Deprecated
    public void setPropertyAffinity(boolean useAffinity) {
        // compilerOptions.setPropertyAffinity(useAffinity);
    }

    public void setShadowVariables(boolean shadow) {
        compilerOptions.setShadowVariables(shadow);
    }

    /**
     * @deprecated no longer a compiler option
     * @param collapse ignored
     */
    @Deprecated
    public void setCollapsePropertiesOnExternTypes(boolean collapse) {
        // compilerOptions.setCollapsePropertiesOnExternTypes(collapse);
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

    /**
     * @param value ignored
     * @deprecated no longer a compiler option
     */
    @Deprecated
    public void setNameAnonymousFunctionsOnly(boolean value) {
        //compilerOptions.setNameAnonymousFunctionsOnly(value);
    }

    public void setColorizeErrorOutput(boolean colorizeErrorOutput) {
        compilerOptions.setColorizeErrorOutput(colorizeErrorOutput);
    }

    public void setChainCalls(boolean value) {
        compilerOptions.setChainCalls(value);
    }

    /**
     * @param value ignored
     * @deprecated no longer a compiler option
     */
    @Deprecated
    public void setAcceptConstKeyword(boolean value) {
        // compilerOptions.setAcceptConstKeyword(value);
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

    /**
     *
     * @param enabled to export externs
     * @deprecated use {@link #setExternExports(boolean) }
     */
    @Deprecated
    public void enableExternExports(boolean enabled) {
        //compilerOptions.enableExternExports(enabled);
        compilerOptions.setExternExports(true);
    }

    public void setExternExports(boolean enabled) {
        compilerOptions.setExternExports(true);
    }

    public void setExtraAnnotationNames(Iterable<String> extraAnnotationNames) {
        compilerOptions.setExtraAnnotationNames(extraAnnotationNames);
    }

    public void setOutputCharset(String charsetName) {
        this.setOutputCharset(Charset.forName(charsetName));
    }

    public void setOutputCharset(Charset charsetName) {
        compilerOptions.setOutputCharset(charsetName);
    }

    public void setTweakProcessing(TweakProcessing tweakProcessing) {
        compilerOptions.setTweakProcessing(tweakProcessing);
    }

    public void setLanguageIn(LanguageMode languageIn) {
        compilerOptions.setLanguageIn(languageIn);
    }

    /**
     *
     * @param looseTypes ignored
     * @deprecated no longer a compiler option
     */
    @Deprecated
    public void setLooseTypes(boolean looseTypes) {
        //compilerOptions.setLooseTypes(looseTypes);
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

    /**
     * @param save ignored
     * @deprecated no longer a compiler option
     */
    @Deprecated
    public void setSaveDataStructures(boolean save) {
//        compilerOptions.setSaveDataStructures(save);
    }

    /**
     * @param skipAllPasses ignored
     * @deprecated no longer a compiler option
     */
    @Deprecated
    public void setSkipAllPasses(boolean skipAllPasses) {
        //   compilerOptions.setSkipAllPasses(skipAllPasses);
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

    /**
     * @param value ignored
     * @deprecated does nothing
     */
    @Deprecated
    public void setCheckControlStructures(boolean value) {
    }

    public void setCheckTypes(boolean checkTypes) {
        compilerOptions.setCheckTypes(checkTypes);
    }

    public void setCheckMissingGetCssNameBlacklist(String blackList) {
        compilerOptions.setCheckMissingGetCssNameBlacklist(blackList);
    }

    /**
     * @param value ignored
     * @deprecated no longer a compiler option
     */
    @Deprecated
    public void setAggressiveRenaming(boolean value) {
        // compilerOptions.setAggressiveRenaming(aggressive);
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

    /**
     * @param value ignored
     * @deprecated no longer a compiler option
     */
    @Deprecated
    public void setAliasExternals(boolean value) {
        //compilerOptions.setAliasExternals(aliasExternals);
    }

    public void setCollapseVariableDeclarations(boolean enabled) {
        compilerOptions.setCollapseVariableDeclarations(enabled);
    }

    /**
     * @param value ignored
     * @deprecated no longer a compiler option
     */
    @Deprecated
    public void setGroupVariableDeclarations(boolean value) {
        //compilerOptions.setGroupVariableDeclarations(enabled);
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

    /**
     * @param value ignored
     * @deprecated no longer a compiler option
     */
    @Deprecated
    public void setAliasKeywords(boolean value) {
        //  compilerOptions.setAliasKeywords(aliasKeywords);
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

    /**
     * @param value ignored
     * @deprecated no longer a compiler option
     */
    @Deprecated
    public void setCustomPasses(Multimap<CustomPassExecutionTime, CompilerPass> value) {
        // compilerOptions.setCustomPasses(customPasses);
    }

    public void addCustomPass(CustomPassExecutionTime time, CompilerPass customPass) {
        compilerOptions.addCustomPass(time, customPass);
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

    public void setInstrumentationTemplate(Instrumentation instrumentationTemplate) {
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

    /**
     * @param instrumentMemoryAllocations ignored
     * @deprecated no longer a compiler option
     */
    @Deprecated
    public void setInstrumentMemoryAllocations(boolean instrumentMemoryAllocations) {
        // compilerOptions.setInstrumentMemoryAllocations(instrumentMemoryAllocations);
    }

    public void setInstrumentForCoverage(boolean instrumentForCoverage) {
        compilerOptions.setInstrumentForCoverage(instrumentForCoverage);
    }

    public void setJqueryPass(boolean jqueryPass) {
        this.compilerOptions.jqueryPass = jqueryPass;
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
        // TODO make useful output
        return this.compilerOptions.toString();
    }

    public CompilerOptions getCompilerOptions() {
        return this.compilerOptions;
    }
}
