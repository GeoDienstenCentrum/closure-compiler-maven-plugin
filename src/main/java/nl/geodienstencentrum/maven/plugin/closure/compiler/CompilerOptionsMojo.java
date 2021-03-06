/*
 * Copyright 2015-2017 Mark Prins, GeoDienstenCentrum.
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

import com.google.javascript.jscomp.AnonymousFunctionNamingPolicy;
import com.google.javascript.jscomp.CheckLevel;
import com.google.javascript.jscomp.CodingConvention;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.CompilerOptions.AliasTransformationHandler;
import com.google.javascript.jscomp.CompilerOptions.Environment;
import com.google.javascript.jscomp.CompilerOptions.J2clPassMode;
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

    public void setPolymerVersion(Integer polymerVersion) {
        this.compilerOptions.setPolymerVersion(polymerVersion); 
    }

    public void setDartPass(boolean dartPass) {
        this.compilerOptions.setDartPass(dartPass);
    }

    public void setChecksOnly(boolean checksOnly) {
        this.compilerOptions.setChecksOnly(checksOnly);
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

    public void setCollapseObjectLiterals(boolean enabled) {
        compilerOptions.setCollapseObjectLiterals(enabled);
    }

    public void setReplaceMessagesWithChromeI18n(boolean replaceMessagesWithChromeI18n, String tcProjectId) {
        compilerOptions.setReplaceMessagesWithChromeI18n(replaceMessagesWithChromeI18n, tcProjectId);
    }

    public void setPreferSingleQuotes(boolean enabled) {
        compilerOptions.setPreferSingleQuotes(enabled);
    }

    public void setTrustedStrings(boolean yes) {
        compilerOptions.setTrustedStrings(yes);
    }

    public void setTracerMode(TracerMode mode) {
        compilerOptions.setTracerMode(mode);
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

    public void setShadowVariables(boolean shadow) {
        compilerOptions.setShadowVariables(shadow);
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

    public void setColorizeErrorOutput(boolean colorizeErrorOutput) {
        compilerOptions.setColorizeErrorOutput(colorizeErrorOutput);
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

    /**
     * Configures the compiler for use as an IDE backend.  In this mode:
     * <ul>
     *  <li>No optimization passes will run.</li>
     *  <li>The last time custom passes are invoked is
     *      {@link CustomPassExecutionTime#BEFORE_OPTIMIZATIONS}</li>
     *  <li>The compiler will always try to process all inputs fully, even
     *      if it encounters errors.</li>
     *  <li>The compiler may record more information than is strictly
     *      needed for codegen.</li>
     * </ul>
     *
     * @deprecated Some "IDE" clients will need some of these options but not
     * others. Consider calling setChecksOnly, setAllowRecompilation, etc,
     * explicitly, instead of calling this method which does a variety of
     * different things.
     */
    @Deprecated
    public void setIdeMode(boolean ideMode) {
        compilerOptions.setIdeMode(ideMode);
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

    public void setCheckTypes(boolean checkTypes) {
        compilerOptions.setCheckTypes(checkTypes);
    }

    public void setCheckMissingGetCssNameBlacklist(String blackList) {
        compilerOptions.setCheckMissingGetCssNameBlacklist(blackList);
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

    public void setParentModuleCanSeeSymbolsDeclaredInChildren(boolean parentModuleCanSeeSymbolsDeclaredInChildren) {
        compilerOptions.setParentModuleCanSeeSymbolsDeclaredInChildren(parentModuleCanSeeSymbolsDeclaredInChildren);
    }

    public void setCoalesceVariableNames(boolean coalesceVariableNames) {
        compilerOptions.setCoalesceVariableNames(coalesceVariableNames);
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

    public void setCollapseVariableDeclarations(boolean enabled) {
        compilerOptions.setCollapseVariableDeclarations(enabled);
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
        // TODO make useful output
        return this.compilerOptions.toString();
    }

    public CompilerOptions getCompilerOptions() {
        return this.compilerOptions;
    }
}
