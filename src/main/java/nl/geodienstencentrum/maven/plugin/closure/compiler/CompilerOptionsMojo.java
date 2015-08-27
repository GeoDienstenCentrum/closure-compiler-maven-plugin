package nl.geodienstencentrum.maven.plugin.closure.compiler;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Multimap;
import com.google.javascript.jscomp.AnonymousFunctionNamingPolicy;
import com.google.javascript.jscomp.CheckLevel;
import com.google.javascript.jscomp.CodingConvention;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.CompilerOptions.AliasTransformationHandler;
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
import com.google.javascript.jscomp.ErrorFormat;
import com.google.javascript.jscomp.ErrorHandler;
import com.google.javascript.jscomp.MessageBundle;
import com.google.javascript.jscomp.PropertyRenamingPolicy;
import com.google.javascript.jscomp.SourceMap.DetailLevel;
import com.google.javascript.jscomp.SourceMap.Format;
import com.google.javascript.jscomp.SourceMap.LocationMapping;
import com.google.javascript.jscomp.VariableRenamingPolicy;
import com.google.javascript.jscomp.WarningsGuard;
import com.google.javascript.rhino.Node;

/**
 * Created: Oct 15, 2012 7:08:55 PM
 *
 * @author mbryant
 *
 */
public class CompilerOptionsMojo {

    private CompilerOptions compilerOptions = new CompilerOptions();

    /**
     * @param guard
     * @see com.google.javascript.jscomp.CompilerOptions#addWarningsGuard(com.google.javascript.jscomp.WarningsGuard)
     */
    public void addWarningsGuard(final WarningsGuard guard) {
        this.compilerOptions.addWarningsGuard(guard);
    }

    /**
     * @see com.google.javascript.jscomp.CompilerOptions#assumeClosuresOnlyCaptureReferences()
     */
    public boolean assumeClosuresOnlyCaptureReferences() {
        return this.compilerOptions.assumeClosuresOnlyCaptureReferences();
    }

    /**
     * @see com.google.javascript.jscomp.CompilerOptions#assumeStrictThis()
     */
    public boolean assumeStrictThis() {
        return this.compilerOptions.assumeStrictThis();
    }

    /**
     * @see com.google.javascript.jscomp.CompilerOptions#disableRuntimeTypeCheck()
     */
    public void disableRuntimeTypeCheck() {
        this.compilerOptions.disableRuntimeTypeCheck();
    }

    /**
     * @see com.google.javascript.jscomp.CompilerOptions#enableRuntimeTypeCheck(java.lang.String)
     */
    public void enableRuntimeTypeCheck(final String logFunction) {
        this.compilerOptions.enableRuntimeTypeCheck(logFunction);
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        return this.compilerOptions.equals(obj);
    }

    /**
     * @see
     * com.google.javascript.jscomp.CompilerOptions#getAliasTransformationHandler()
     */
    public AliasTransformationHandler getAliasTransformationHandler() {
        return this.compilerOptions.getAliasTransformationHandler();
    }

    /**
     * @see com.google.javascript.jscomp.CompilerOptions#getCodingConvention()
     */
    public CodingConvention getCodingConvention() {
        return this.compilerOptions.getCodingConvention();
    }

    /**
     * @return the compilerOptions
     */
    public CompilerOptions getCompilerOptions() {
        return this.compilerOptions;
    }

    /**
     * @see com.google.javascript.jscomp.CompilerOptions#getDefineReplacements()
     */
    public Map<String, Node> getDefineReplacements() {
        return this.compilerOptions.getDefineReplacements();
    }

    /**
     * @see com.google.javascript.jscomp.CompilerOptions#getInferTypes()
     */
    public boolean getInferTypes() {
        return this.compilerOptions.getInferTypes();
    }

    /**
     * @see com.google.javascript.jscomp.CompilerOptions#getLanguageIn()
     */
    public LanguageMode getLanguageIn() {
        return this.compilerOptions.getLanguageIn();
    }

    /**
     * @see com.google.javascript.jscomp.CompilerOptions#getLanguageOut()
     */
    public LanguageMode getLanguageOut() {
        return this.compilerOptions.getLanguageOut();
    }

    /**
     * @see com.google.javascript.jscomp.CompilerOptions#getTracerMode()
     */
    public TracerMode getTracerMode() {
        return this.compilerOptions.getTracerMode();
    }

    /**
     * @see com.google.javascript.jscomp.CompilerOptions#getTweakProcessing()
     */
    public TweakProcessing getTweakProcessing() {
        return this.compilerOptions.getTweakProcessing();
    }

    /**
     * @see com.google.javascript.jscomp.CompilerOptions#getTweakReplacements()
     */
    public Map<String, Node> getTweakReplacements() {
        return this.compilerOptions.getTweakReplacements();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return this.compilerOptions.hashCode();
    }

    /**
     * @see
     * com.google.javascript.jscomp.CompilerOptions#isExternExportsEnabled()
     */
    public boolean isExternExportsEnabled() {
        return this.compilerOptions.isExternExportsEnabled();
    }

    /**
     * @see
     * com.google.javascript.jscomp.CompilerOptions#isRemoveUnusedClassProperties()
     */
    public boolean isRemoveUnusedClassProperties() {
        return this.compilerOptions.isRemoveUnusedClassProperties();
    }

    /**
     * @see com.google.javascript.jscomp.CompilerOptions#resetWarningsGuard()
     */
    public void resetWarningsGuard() {
        this.compilerOptions.resetWarningsGuard();
    }

    /**
     * @param value
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setAcceptConstKeyword(boolean)
     */
    public void setAcceptConstKeyword(final boolean value) {
        this.compilerOptions.setAcceptConstKeyword(value);
    }

    /**
     * @param level
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setAggressiveVarCheck(com.google.javascript.jscomp.CheckLevel)
     */
    public void setAggressiveVarCheck(final CheckLevel level) {
        this.compilerOptions.setAggressiveVarCheck(level);
    }

    /**
     * @param names
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setAliasableGlobals(java.lang.String)
     */
    public void setAliasableGlobals(final String names) {
        this.compilerOptions.setAliasableGlobals(names);
    }

    /**
     * @param aliasableStrings
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setAliasableStrings(java.util.Set)
     */
    public void setAliasableStrings(final Set<String> aliasableStrings) {
        this.compilerOptions.setAliasableStrings(aliasableStrings);
    }

    /**
     * @param aliasAllStrings
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setAliasAllStrings(boolean)
     */
    public void setAliasAllStrings(final boolean aliasAllStrings) {
        this.compilerOptions.setAliasAllStrings(aliasAllStrings);
    }

    /**
     * @param aliasExternals
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setAliasExternals(boolean)
     */
    public void setAliasExternals(final boolean aliasExternals) {
        this.compilerOptions.setAliasExternals(aliasExternals);
    }

    /**
     * @param aliasKeywords
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setAliasKeywords(boolean)
     */
    public void setAliasKeywords(final boolean aliasKeywords) {
        this.compilerOptions.setAliasKeywords(aliasKeywords);
    }

    /**
     * @param aliasStringsBlacklist
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setAliasStringsBlacklist(java.lang.String)
     */
    public void setAliasStringsBlacklist(final String aliasStringsBlacklist) {
        this.compilerOptions.setAliasStringsBlacklist(aliasStringsBlacklist);
    }

    /**
     * @param changes
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setAliasTransformationHandler(com.google.javascript.jscomp.CompilerOptions.AliasTransformationHandler)
     */
    public void setAliasTransformationHandler(
            final AliasTransformationHandler changes) {
        this.compilerOptions.setAliasTransformationHandler(changes);
    }

    /**
     * @param ambiguateProperties
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setAmbiguateProperties(boolean)
     */
    public void setAmbiguateProperties(final boolean ambiguateProperties) {
        this.compilerOptions.setAmbiguateProperties(ambiguateProperties);
    }

    /**
     * @param anonymousFunctionNaming
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setAnonymousFunctionNaming(com.google.javascript.jscomp.AnonymousFunctionNamingPolicy)
     */
    public void setAnonymousFunctionNaming(
            final AnonymousFunctionNamingPolicy anonymousFunctionNaming) {
        this.compilerOptions
                .setAnonymousFunctionNaming(anonymousFunctionNaming);
    }

    /**
     * @param appNameStr
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setAppNameStr(java.lang.String)
     */
    public void setAppNameStr(final String appNameStr) {
        this.compilerOptions.setAppNameStr(appNameStr);
    }

    /**
     * @param enable
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setAssumeClosuresOnlyCaptureReferences(boolean)
     */
    public void setAssumeClosuresOnlyCaptureReferences(final boolean enable) {
        this.compilerOptions.setAssumeClosuresOnlyCaptureReferences(enable);
    }

    /**
     * @param enable
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setAssumeStrictThis(boolean)
     */
    public void setAssumeStrictThis(final boolean enable) {
        this.compilerOptions.setAssumeStrictThis(enable);
    }

    /**
     * @param level
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setBrokenClosureRequiresLevel(com.google.javascript.jscomp.CheckLevel)
     */
    public void setBrokenClosureRequiresLevel(final CheckLevel level) {
        this.compilerOptions.setBrokenClosureRequiresLevel(level);
    }

    /**
     * @param value
     * @see com.google.javascript.jscomp.CompilerOptions#setChainCalls(boolean)
     */
    public void setChainCalls(final boolean value) {
        this.compilerOptions.setChainCalls(value);
    }

    /**
     * @param check
     * @see com.google.javascript.jscomp.CompilerOptions#setCheckCaja(boolean)
     */
    public void setCheckCaja(final boolean check) {
        this.compilerOptions.setCheckCaja(check);
    }

    /**
     * @param checkControlStructures
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setCheckControlStructures(boolean)
     */
    public void setCheckControlStructures(final boolean checkControlStructures) {
        this.compilerOptions.setCheckControlStructures(checkControlStructures);
    }

    /**
     * @param level
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setCheckGlobalNamesLevel(com.google.javascript.jscomp.CheckLevel)
     */
    public void setCheckGlobalNamesLevel(final CheckLevel level) {
        this.compilerOptions.setCheckGlobalNamesLevel(level);
    }

    /**
     * @param level
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setCheckGlobalThisLevel(com.google.javascript.jscomp.CheckLevel)
     */
    public void setCheckGlobalThisLevel(final CheckLevel level) {
        this.compilerOptions.setCheckGlobalThisLevel(level);
    }

    /**
     * @param blackList
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setCheckMissingGetCssNameBlacklist(java.lang.String)
     */
    public void setCheckMissingGetCssNameBlacklist(final String blackList) {
        this.compilerOptions.setCheckMissingGetCssNameBlacklist(blackList);
    }

    /**
     * @param level
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setCheckMissingGetCssNameLevel(com.google.javascript.jscomp.CheckLevel)
     */
    public void setCheckMissingGetCssNameLevel(final CheckLevel level) {
        this.compilerOptions.setCheckMissingGetCssNameLevel(level);
    }

    /**
     * @param level
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setCheckMissingReturn(com.google.javascript.jscomp.CheckLevel)
     */
    public void setCheckMissingReturn(final CheckLevel level) {
        this.compilerOptions.setCheckMissingReturn(level);
    }

    /**
     * @param level
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setCheckProvides(com.google.javascript.jscomp.CheckLevel)
     */
    public void setCheckProvides(final CheckLevel level) {
        this.compilerOptions.setCheckProvides(level);
    }

    /**
     * @param level
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setCheckRequires(com.google.javascript.jscomp.CheckLevel)
     */
    public void setCheckRequires(final CheckLevel level) {
        this.compilerOptions.setCheckRequires(level);
    }

    /**
     * @param checkSuspiciousCode
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setCheckSuspiciousCode(boolean)
     */
    public void setCheckSuspiciousCode(final boolean checkSuspiciousCode) {
        this.compilerOptions.setCheckSuspiciousCode(checkSuspiciousCode);
    }

    /**
     * @param checkSymbols
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setCheckSymbols(boolean)
     */
    public void setCheckSymbols(final boolean checkSymbols) {
        this.compilerOptions.setCheckSymbols(checkSymbols);
    }

    /**
     * @param checkTypes
     * @see com.google.javascript.jscomp.CompilerOptions#setCheckTypes(boolean)
     */
    public void setCheckTypes(final boolean checkTypes) {
        this.compilerOptions.setCheckTypes(checkTypes);
    }

    /**
     * @param level
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setCheckUnreachableCode(com.google.javascript.jscomp.CheckLevel)
     */
    public void setCheckUnreachableCode(final CheckLevel level) {
        this.compilerOptions.setCheckUnreachableCode(level);
    }

    /**
     * @param closurePass
     * @see com.google.javascript.jscomp.CompilerOptions#setClosurePass(boolean)
     */
    public void setClosurePass(final boolean closurePass) {
        this.compilerOptions.setClosurePass(closurePass);
    }

    /**
     * @param coalesceVariableNames
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setCoalesceVariableNames(boolean)
     */
    public void setCoalesceVariableNames(final boolean coalesceVariableNames) {
        this.compilerOptions.setCoalesceVariableNames(coalesceVariableNames);
    }

    /**
     * @param codingConvention
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setCodingConvention(com.google.javascript.jscomp.CodingConvention)
     */
    public void setCodingConvention(final CodingConvention codingConvention) {
        this.compilerOptions.setCodingConvention(codingConvention);
    }

    /**
     * @param enabled
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setCollapseAnonymousFunctions(boolean)
     */
    public void setCollapseAnonymousFunctions(final boolean enabled) {
        this.compilerOptions.setCollapseAnonymousFunctions(enabled);
    }

    /**
     * @param enabled
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setCollapseObjectLiterals(boolean)
     */
    public void setCollapseObjectLiterals(final boolean enabled) {
        this.compilerOptions.setCollapseObjectLiterals(enabled);
    }

    /**
     * @param collapseProperties
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setCollapseProperties(boolean)
     */
    public void setCollapseProperties(final boolean collapseProperties) {
        this.compilerOptions.setCollapseProperties(collapseProperties);
    }

    /**
     * @param collapse
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setCollapsePropertiesOnExternTypes(boolean)
     */
    public void setCollapsePropertiesOnExternTypes(final boolean collapse) {
        this.compilerOptions.setCollapsePropertiesOnExternTypes(collapse);
    }

    /**
     * @param enabled
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setCollapseVariableDeclarations(boolean)
     */
    public void setCollapseVariableDeclarations(final boolean enabled) {
        this.compilerOptions.setCollapseVariableDeclarations(enabled);
    }

    /**
     * @param colorizeErrorOutput
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setColorizeErrorOutput(boolean)
     */
    public void setColorizeErrorOutput(final boolean colorizeErrorOutput) {
        this.compilerOptions.setColorizeErrorOutput(colorizeErrorOutput);
    }

    /**
     * @param commonJSModulePathPrefix
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setCommonJSModulePathPrefix(java.lang.String)
     */
    public void setCommonJSModulePathPrefix(
            final String commonJSModulePathPrefix) {
        this.compilerOptions
                .setCommonJSModulePathPrefix(commonJSModulePathPrefix);
    }

    /**
     * @param compilerOptions the compilerOptions to set
     */
    public void setCompilerOptions(final CompilerOptions compilerOptions) {
        this.compilerOptions = compilerOptions;
    }

    /**
     * @param computeFunctionSideEffects
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setComputeFunctionSideEffects(boolean)
     */
    public void setComputeFunctionSideEffects(
            final boolean computeFunctionSideEffects) {
        this.compilerOptions
                .setComputeFunctionSideEffects(computeFunctionSideEffects);
    }

    /**
     * @param convertToDottedProperties
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setConvertToDottedProperties(boolean)
     */
    public void setConvertToDottedProperties(
            final boolean convertToDottedProperties) {
        this.compilerOptions
                .setConvertToDottedProperties(convertToDottedProperties);
    }

    /**
     * @param crossModuleCodeMotion
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setCrossModuleCodeMotion(boolean)
     */
    public void setCrossModuleCodeMotion(final boolean crossModuleCodeMotion) {
        this.compilerOptions.setCrossModuleCodeMotion(crossModuleCodeMotion);
    }

    /**
     * @param crossModuleMethodMotion
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setCrossModuleMethodMotion(boolean)
     */
    public void setCrossModuleMethodMotion(final boolean crossModuleMethodMotion) {
        this.compilerOptions
                .setCrossModuleMethodMotion(crossModuleMethodMotion);
    }

    /**
     * @param cssRenamingMap
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setCssRenamingMap(com.google.javascript.jscomp.CssRenamingMap)
     */
    public void setCssRenamingMap(final CssRenamingMap cssRenamingMap) {
        this.compilerOptions.setCssRenamingMap(cssRenamingMap);
    }

    /**
     * @param customPasses
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setCustomPasses(com.google.common.collect.Multimap)
     */
    public void setCustomPasses(
            final Multimap<CustomPassExecutionTime, CompilerPass> customPasses) {
        this.compilerOptions.setCustomPasses(customPasses);
    }

    /**
     * @param deadAssignmentElimination
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setDeadAssignmentElimination(boolean)
     */
    public void setDeadAssignmentElimination(
            final boolean deadAssignmentElimination) {
        this.compilerOptions
                .setDeadAssignmentElimination(deadAssignmentElimination);
    }

    /**
     * @param debugFunctionSideEffectsPath
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setDebugFunctionSideEffectsPath(java.lang.String)
     */
    public void setDebugFunctionSideEffectsPath(
            final String debugFunctionSideEffectsPath) {
        this.compilerOptions
                .setDebugFunctionSideEffectsPath(debugFunctionSideEffectsPath);
    }

    /**
     * @param defineReplacements
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setDefineReplacements(java.util.Map)
     */
    public void setDefineReplacements(
            final Map<String, Object> defineReplacements) {
        this.compilerOptions.setDefineReplacements(defineReplacements);
    }

    /**
     * @param defineName
     * @param value
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setDefineToBooleanLiteral(java.lang.String,
     * boolean)
     */
    public void setDefineToBooleanLiteral(final String defineName,
            final boolean value) {
        this.compilerOptions.setDefineToBooleanLiteral(defineName, value);
    }

    /**
     * @param defineName
     * @param value
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setDefineToDoubleLiteral(java.lang.String,
     * double)
     */
    public void setDefineToDoubleLiteral(final String defineName,
            final double value) {
        this.compilerOptions.setDefineToDoubleLiteral(defineName, value);
    }

    /**
     * @param defineName
     * @param value
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setDefineToNumberLiteral(java.lang.String,
     * int)
     */
    public void setDefineToNumberLiteral(final String defineName,
            final int value) {
        this.compilerOptions.setDefineToNumberLiteral(defineName, value);
    }

    /**
     * @param defineName
     * @param value
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setDefineToStringLiteral(java.lang.String,
     * java.lang.String)
     */
    public void setDefineToStringLiteral(final String defineName,
            final String value) {
        this.compilerOptions.setDefineToStringLiteral(defineName, value);
    }

    /**
     * @param options
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setDependencyOptions(com.google.javascript.jscomp.DependencyOptions)
     */
    public void setDependencyOptions(final DependencyOptions options) {
        this.compilerOptions.setDependencyOptions(options);
    }

    /**
     * @param devirtualizePrototypeMethods
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setDevirtualizePrototypeMethods(boolean)
     */
    public void setDevirtualizePrototypeMethods(
            final boolean devirtualizePrototypeMethods) {
        this.compilerOptions
                .setDevirtualizePrototypeMethods(devirtualizePrototypeMethods);
    }

    /**
     * @param disambiguateProperties
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setDisambiguateProperties(boolean)
     */
    public void setDisambiguateProperties(final boolean disambiguateProperties) {
        this.compilerOptions.setDisambiguateProperties(disambiguateProperties);
    }

    /**
     * @param errorFormat
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setErrorFormat(com.google.javascript.jscomp.ErrorFormat)
     */
    public void setErrorFormat(final ErrorFormat errorFormat) {
        this.compilerOptions.setErrorFormat(errorFormat);
    }

    /**
     * @param handler
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setErrorHandler(com.google.javascript.jscomp.ErrorHandler)
     */
    public void setErrorHandler(final ErrorHandler handler) {
        this.compilerOptions.setErrorHandler(handler);
    }

    /**
     * @param exportTestFunctions
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setExportTestFunctions(boolean)
     */
    public void setExportTestFunctions(final boolean exportTestFunctions) {
        this.compilerOptions.setExportTestFunctions(exportTestFunctions);
    }

    /**
     * @param externExports
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setExternExports(boolean)
     */
    public void setExternExports(final boolean externExports) {
        this.compilerOptions.setExternExports(externExports);
    }

    /**
     * @param externExportsPath
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setExternExportsPath(java.lang.String)
     */
    public void setExternExportsPath(final String externExportsPath) {
        this.compilerOptions.setExternExportsPath(externExportsPath);
    }

    /**
     * @param extraAnnotationNames
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setExtraAnnotationNames(java.util.Set)
     */
    public void setExtraAnnotationNames(final Set<String> extraAnnotationNames) {
        this.compilerOptions.setExtraAnnotationNames(extraAnnotationNames);
    }

    /**
     * @param enabled
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setExtractPrototypeMemberDeclarations(boolean)
     */
    public void setExtractPrototypeMemberDeclarations(final boolean enabled) {
        this.compilerOptions.setExtractPrototypeMemberDeclarations(enabled);
    }

    /**
     * @param enabled
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setFlowSensitiveInlineVariables(boolean)
     */
    public void setFlowSensitiveInlineVariables(final boolean enabled) {
        this.compilerOptions.setFlowSensitiveInlineVariables(enabled);
    }

    /**
     * @param foldConstants
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setFoldConstants(boolean)
     */
    public void setFoldConstants(final boolean foldConstants) {
        this.compilerOptions.setFoldConstants(foldConstants);
    }

    /**
     * @param gatherCssNames
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setGatherCssNames(boolean)
     */
    public void setGatherCssNames(final boolean gatherCssNames) {
        this.compilerOptions.setGatherCssNames(gatherCssNames);
    }

    /**
     * @param generateExports
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setGenerateExports(boolean)
     */
    public void setGenerateExports(final boolean generateExports) {
        this.compilerOptions.setGenerateExports(generateExports);
    }

    /**
     * @param generatePseudoNames
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setGeneratePseudoNames(boolean)
     */
    public void setGeneratePseudoNames(final boolean generatePseudoNames) {
        this.compilerOptions.setGeneratePseudoNames(generatePseudoNames);
    }

    /**
     * @param enabled
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setGroupVariableDeclarations(boolean)
     */
    public void setGroupVariableDeclarations(final boolean enabled) {
        this.compilerOptions.setGroupVariableDeclarations(enabled);
    }

    /**
     * @param ideMode
     * @see com.google.javascript.jscomp.CompilerOptions#setIdeMode(boolean)
     */
    public void setIdeMode(final boolean ideMode) {
        this.compilerOptions.setIdeMode(ideMode);
    }

    /**
     * @param idGenerators
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setIdGenerators(java.util.Set)
     */
    public void setIdGenerators(final Set<String> idGenerators) {
        this.compilerOptions.setIdGenerators(idGenerators);
    }

    /**
     * @param enabled
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setIgnoreCajaProperties(boolean)
     */
    public void setIgnoreCajaProperties(final boolean enabled) {
        this.compilerOptions.setIgnoreCajaProperties(enabled);
    }

    /**
     * @param enable
     * @see com.google.javascript.jscomp.CompilerOptions#setInferTypes(boolean)
     */
    public void setInferTypes(final boolean enable) {
        this.compilerOptions.setInferTypes(enable);
    }

    /**
     * @param inlineConstantVars
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setInlineConstantVars(boolean)
     */
    public void setInlineConstantVars(final boolean inlineConstantVars) {
        this.compilerOptions.setInlineConstantVars(inlineConstantVars);
    }

    /**
     * @param inlineFunctions
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setInlineFunctions(boolean)
     */
    public void setInlineFunctions(final boolean inlineFunctions) {
        this.compilerOptions.setInlineFunctions(inlineFunctions);
    }

    /**
     * @param reach
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setInlineFunctions(com.google.javascript.jscomp.CompilerOptions.Reach)
     */
    public void setInlineFunctions(final Reach reach) {
        this.compilerOptions.setInlineFunctions(reach);
    }

    /**
     * @param inlineGetters
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setInlineGetters(boolean)
     */
    public void setInlineGetters(final boolean inlineGetters) {
        this.compilerOptions.setInlineGetters(inlineGetters);
    }

    /**
     * @param inlineLocalFunctions
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setInlineLocalFunctions(boolean)
     */
    public void setInlineLocalFunctions(final boolean inlineLocalFunctions) {
        this.compilerOptions.setInlineLocalFunctions(inlineLocalFunctions);
    }

    /**
     * @param inlineLocalVariables
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setInlineLocalVariables(boolean)
     */
    public void setInlineLocalVariables(final boolean inlineLocalVariables) {
        this.compilerOptions.setInlineLocalVariables(inlineLocalVariables);
    }

    /**
     * @param enable
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setInlineProperties(boolean)
     */
    public void setInlineProperties(final boolean enable) {
        this.compilerOptions.setInlineProperties(enable);
    }

    /**
     * @param inlineVariables
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setInlineVariables(boolean)
     */
    public void setInlineVariables(final boolean inlineVariables) {
        this.compilerOptions.setInlineVariables(inlineVariables);
    }

    /**
     * @param reach
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setInlineVariables(com.google.javascript.jscomp.CompilerOptions.Reach)
     */
    public void setInlineVariables(final Reach reach) {
        this.compilerOptions.setInlineVariables(reach);
    }

    /**
     * @param inputDelimiter
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setInputDelimiter(java.lang.String)
     */
    public void setInputDelimiter(final String inputDelimiter) {
        this.compilerOptions.setInputDelimiter(inputDelimiter);
    }

    /**
     * @param inputPropertyMapSerialized
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setInputPropertyMapSerialized(byte[])
     */
    public void setInputPropertyMapSerialized(
            final byte[] inputPropertyMapSerialized) {
        this.compilerOptions
                .setInputPropertyMapSerialized(inputPropertyMapSerialized);
    }

    /**
     * @param inputVariableMapSerialized
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setInputVariableMapSerialized(byte[])
     */
    public void setInputVariableMapSerialized(
            final byte[] inputVariableMapSerialized) {
        this.compilerOptions
                .setInputVariableMapSerialized(inputVariableMapSerialized);
    }

    /**
     * @param instrumentationTemplate
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setInstrumentationTemplate(java.lang.String)
     */
    public void setInstrumentationTemplate(final String instrumentationTemplate) {
        this.compilerOptions
                .setInstrumentationTemplate(instrumentationTemplate);
    }

    /**
     * @param labelRenaming
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setLabelRenaming(boolean)
     */
    public void setLabelRenaming(final boolean labelRenaming) {
        this.compilerOptions.setLabelRenaming(labelRenaming);
    }

    /**
     * @param languageIn
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setLanguageIn(com.google.javascript.jscomp.CompilerOptions.LanguageMode)
     */
    public void setLanguageIn(final LanguageMode languageIn) {
        this.compilerOptions.setLanguageIn(languageIn);
    }

    /**
     * @param languageOut
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setLanguageOut(com.google.javascript.jscomp.CompilerOptions.LanguageMode)
     */
    public void setLanguageOut(final LanguageMode languageOut) {
        this.compilerOptions.setLanguageOut(languageOut);
    }

    /**
     * @param lineBreak
     * @see com.google.javascript.jscomp.CompilerOptions#setLineBreak(boolean)
     */
    public void setLineBreak(final boolean lineBreak) {
        this.compilerOptions.setLineBreak(lineBreak);
    }

    /**
     * @param lineLengthThreshold
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setLineLengthThreshold(int)
     */
    public void setLineLengthThreshold(final int lineLengthThreshold) {
        this.compilerOptions.setLineLengthThreshold(lineLengthThreshold);
    }

    /**
     * @param locale
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setLocale(java.lang.String)
     */
    public void setLocale(final String locale) {
        this.compilerOptions.setLocale(locale);
    }

    /**
     * @param looseTypes
     * @see com.google.javascript.jscomp.CompilerOptions#setLooseTypes(boolean)
     */
    public void setLooseTypes(final boolean looseTypes) {
        this.compilerOptions.setLooseTypes(looseTypes);
    }

    /**
     * @param newVal
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setManageClosureDependencies(boolean)
     */
    public void setManageClosureDependencies(final boolean newVal) {
        this.compilerOptions.setManageClosureDependencies(newVal);
    }

    /**
     * @param entryPoints
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setManageClosureDependencies(java.util.List)
     */
    public void setManageClosureDependencies(final List<String> entryPoints) {
        this.compilerOptions.setManageClosureDependencies(entryPoints);
    }

    /**
     * @param markAsCompiled
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setMarkAsCompiled(boolean)
     */
    public void setMarkAsCompiled(final boolean markAsCompiled) {
        this.compilerOptions.setMarkAsCompiled(markAsCompiled);
    }

    /**
     * @param markNoSideEffectCalls
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setMarkNoSideEffectCalls(boolean)
     */
    public void setMarkNoSideEffectCalls(final boolean markNoSideEffectCalls) {
        this.compilerOptions.setMarkNoSideEffectCalls(markNoSideEffectCalls);
    }

    /**
     * @param messageBundle
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setMessageBundle(com.google.javascript.jscomp.MessageBundle)
     */
    public void setMessageBundle(final MessageBundle messageBundle) {
        this.compilerOptions.setMessageBundle(messageBundle);
    }

    /**
     * @param moveFunctionDeclarations
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setMoveFunctionDeclarations(boolean)
     */
    public void setMoveFunctionDeclarations(
            final boolean moveFunctionDeclarations) {
        this.compilerOptions
                .setMoveFunctionDeclarations(moveFunctionDeclarations);
    }

    /**
     * @param value
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setNameAnonymousFunctionsOnly(boolean)
     */
    public void setNameAnonymousFunctionsOnly(final boolean value) {
        this.compilerOptions.setNameAnonymousFunctionsOnly(value);
    }

    /**
     * @param filePath
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setNameReferenceGraphPath(java.lang.String)
     */
    public void setNameReferenceGraphPath(final String filePath) {
        this.compilerOptions.setNameReferenceGraphPath(filePath);
    }

    /**
     * @param filePath
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setNameReferenceReportPath(java.lang.String)
     */
    public void setNameReferenceReportPath(final String filePath) {
        this.compilerOptions.setNameReferenceReportPath(filePath);
    }

    /**
     * @param optimizeArgumentsArray
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setOptimizeArgumentsArray(boolean)
     */
    public void setOptimizeArgumentsArray(final boolean optimizeArgumentsArray) {
        this.compilerOptions.setOptimizeArgumentsArray(optimizeArgumentsArray);
    }

    /**
     * @param optimizeCalls
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setOptimizeCalls(boolean)
     */
    public void setOptimizeCalls(final boolean optimizeCalls) {
        this.compilerOptions.setOptimizeCalls(optimizeCalls);
    }

    /**
     * @param optimizeParameters
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setOptimizeParameters(boolean)
     */
    public void setOptimizeParameters(final boolean optimizeParameters) {
        this.compilerOptions.setOptimizeParameters(optimizeParameters);
    }

    /**
     * @param optimizeReturns
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setOptimizeReturns(boolean)
     */
    public void setOptimizeReturns(final boolean optimizeReturns) {
        this.compilerOptions.setOptimizeReturns(optimizeReturns);
    }

    /**
     * @param charsetName
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setOutputCharset(java.lang.String)
     */
    public void setOutputCharset(final String charsetName) {
        this.compilerOptions.setOutputCharset(charsetName);
    }

    /**
     * @param outputJsStringUsage
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setOutputJsStringUsage(boolean)
     */
    public void setOutputJsStringUsage(final boolean outputJsStringUsage) {
        this.compilerOptions.setOutputJsStringUsage(outputJsStringUsage);
    }

    /**
     * @param lineBreakAtEnd
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setPreferLineBreakAtEndOfFile(boolean)
     */
    public void setPreferLineBreakAtEndOfFile(final boolean lineBreakAtEnd) {
        this.compilerOptions.setPreferLineBreakAtEndOfFile(lineBreakAtEnd);
    }

    /**
     * @param prettyPrint
     * @see com.google.javascript.jscomp.CompilerOptions#setPrettyPrint(boolean)
     */
    public void setPrettyPrint(final boolean prettyPrint) {
        this.compilerOptions.setPrettyPrint(prettyPrint);
    }

    /**
     * @param printInputDelimiter
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setPrintInputDelimiter(boolean)
     */
    public void setPrintInputDelimiter(final boolean printInputDelimiter) {
        this.compilerOptions.setPrintInputDelimiter(printInputDelimiter);
    }

    /**
     * @param processCommonJSModules
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setProcessCommonJSModules(boolean)
     */
    public void setProcessCommonJSModules(final boolean processCommonJSModules) {
        this.compilerOptions.setProcessCommonJSModules(processCommonJSModules);
    }

    /**
     * @param process
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setProcessObjectPropertyString(boolean)
     */
    public void setProcessObjectPropertyString(final boolean process) {
        this.compilerOptions.setProcessObjectPropertyString(process);
    }

    /**
     * @param useAffinity
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setPropertyAffinity(boolean)
     */
    public void setPropertyAffinity(final boolean useAffinity) {
        this.compilerOptions.setPropertyAffinity(useAffinity);
    }

    /**
     * @param propertyInvalidationErrors
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setPropertyInvalidationErrors(java.util.Map)
     */
    public void setPropertyInvalidationErrors(
            final Map<String, CheckLevel> propertyInvalidationErrors) {
        this.compilerOptions
                .setPropertyInvalidationErrors(propertyInvalidationErrors);
    }

    /**
     * @param propertyRenaming
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setPropertyRenaming(com.google.javascript.jscomp.PropertyRenamingPolicy)
     */
    public void setPropertyRenaming(
            final PropertyRenamingPolicy propertyRenaming) {
        this.compilerOptions.setPropertyRenaming(propertyRenaming);
    }

    /**
     * @param enable
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setProtectHiddenSideEffects(boolean)
     */
    public void setProtectHiddenSideEffects(final boolean enable) {
        this.compilerOptions.setProtectHiddenSideEffects(enable);
    }

    /**
     * @param recordFunctionInformation
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setRecordFunctionInformation(boolean)
     */
    public void setRecordFunctionInformation(
            final boolean recordFunctionInformation) {
        this.compilerOptions
                .setRecordFunctionInformation(recordFunctionInformation);
    }

    /**
     * @param remove
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setRemoveAbstractMethods(boolean)
     */
    public void setRemoveAbstractMethods(final boolean remove) {
        this.compilerOptions.setRemoveAbstractMethods(remove);
    }

    /**
     * @param remove
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setRemoveClosureAsserts(boolean)
     */
    public void setRemoveClosureAsserts(final boolean remove) {
        this.compilerOptions.setRemoveClosureAsserts(remove);
    }

    /**
     * @param removeDeadCode
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setRemoveDeadCode(boolean)
     */
    public void setRemoveDeadCode(final boolean removeDeadCode) {
        this.compilerOptions.setRemoveDeadCode(removeDeadCode);
    }

    /**
     * @param removeTryCatchFinally
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setRemoveTryCatchFinally(boolean)
     */
    public void setRemoveTryCatchFinally(final boolean removeTryCatchFinally) {
        this.compilerOptions.setRemoveTryCatchFinally(removeTryCatchFinally);
    }

    /**
     * @param removeUnusedClassProperties
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setRemoveUnusedClassProperties(boolean)
     */
    public void setRemoveUnusedClassProperties(
            final boolean removeUnusedClassProperties) {
        this.compilerOptions
                .setRemoveUnusedClassProperties(removeUnusedClassProperties);
    }

    /**
     * @param removeUnusedLocalVars
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setRemoveUnusedLocalVars(boolean)
     */
    public void setRemoveUnusedLocalVars(final boolean removeUnusedLocalVars) {
        this.compilerOptions.setRemoveUnusedLocalVars(removeUnusedLocalVars);
    }

    /**
     * @param enabled
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setRemoveUnusedPrototypeProperties(boolean)
     */
    public void setRemoveUnusedPrototypeProperties(final boolean enabled) {
        this.compilerOptions.setRemoveUnusedPrototypeProperties(enabled);
    }

    /**
     * @param enabled
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setRemoveUnusedPrototypePropertiesInExterns(boolean)
     */
    public void setRemoveUnusedPrototypePropertiesInExterns(
            final boolean enabled) {
        this.compilerOptions
                .setRemoveUnusedPrototypePropertiesInExterns(enabled);
    }

    /**
     * @param reach
     * @deprecated
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setRemoveUnusedVariable(com.google.javascript.jscomp.CompilerOptions.Reach)
     */
    @Deprecated
    public void setRemoveUnusedVariable(final Reach reach) {
        this.compilerOptions.setRemoveUnusedVariable(reach);
    }

    /**
     * @param reach
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setRemoveUnusedVariables(com.google.javascript.jscomp.CompilerOptions.Reach)
     */
    public void setRemoveUnusedVariables(final Reach reach) {
        this.compilerOptions.setRemoveUnusedVariables(reach);
    }

    /**
     * @param removeUnusedVars
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setRemoveUnusedVars(boolean)
     */
    public void setRemoveUnusedVars(final boolean removeUnusedVars) {
        this.compilerOptions.setRemoveUnusedVars(removeUnusedVars);
    }

    /**
     * @param renamePrefix
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setRenamePrefix(java.lang.String)
     */
    public void setRenamePrefix(final String renamePrefix) {
        this.compilerOptions.setRenamePrefix(renamePrefix);
    }

    /**
     * @param renamePrefixNamespace
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setRenamePrefixNamespace(java.lang.String)
     */
    public void setRenamePrefixNamespace(final String renamePrefixNamespace) {
        this.compilerOptions.setRenamePrefixNamespace(renamePrefixNamespace);
    }

    /**
     * @param newVariablePolicy
     * @param newPropertyPolicy
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setRenamingPolicy(com.google.javascript.jscomp.VariableRenamingPolicy,
     * com.google.javascript.jscomp.PropertyRenamingPolicy)
     */
    public void setRenamingPolicy(
            final VariableRenamingPolicy newVariablePolicy,
            final PropertyRenamingPolicy newPropertyPolicy) {
        this.compilerOptions.setRenamingPolicy(newVariablePolicy,
                newPropertyPolicy);
    }

    /**
     * @param replaceIdGenerators
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setReplaceIdGenerators(boolean)
     */
    public void setReplaceIdGenerators(final boolean replaceIdGenerators) {
        this.compilerOptions.setReplaceIdGenerators(replaceIdGenerators);
    }

    /**
     * @param replaceMessagesWithChromeI18n
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setReplaceMessagesWithChromeI18n(boolean)
     */
    public void setReplaceMessagesWithChromeI18n(
            final boolean replaceMessagesWithChromeI18n) {
        this.compilerOptions
                .setReplaceMessagesWithChromeI18n(replaceMessagesWithChromeI18n);
    }

    /**
     * @param placeholderToken
     * @param functionDescriptors
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setReplaceStringsConfiguration(java.lang.String,
     * java.util.List)
     */
    public void setReplaceStringsConfiguration(final String placeholderToken,
            final List<String> functionDescriptors) {
        this.compilerOptions.setReplaceStringsConfiguration(placeholderToken,
                functionDescriptors);
    }

    /**
     * @param replaceStringsFunctionDescriptions
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setReplaceStringsFunctionDescriptions(java.util.List)
     */
    public void setReplaceStringsFunctionDescriptions(
            final List<String> replaceStringsFunctionDescriptions) {
        this.compilerOptions
                .setReplaceStringsFunctionDescriptions(replaceStringsFunctionDescriptions);
    }

    /**
     * @param replaceStringsPlaceholderToken
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setReplaceStringsPlaceholderToken(java.lang.String)
     */
    public void setReplaceStringsPlaceholderToken(
            final String replaceStringsPlaceholderToken) {
        this.compilerOptions
                .setReplaceStringsPlaceholderToken(replaceStringsPlaceholderToken);
    }

    /**
     * @param replaceStringsReservedStrings
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setReplaceStringsReservedStrings(java.util.Set)
     */
    public void setReplaceStringsReservedStrings(
            final Set<String> replaceStringsReservedStrings) {
        this.compilerOptions
                .setReplaceStringsReservedStrings(replaceStringsReservedStrings);
    }

    /**
     * @param level
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setReportMissingOverride(com.google.javascript.jscomp.CheckLevel)
     */
    public void setReportMissingOverride(final CheckLevel level) {
        this.compilerOptions.setReportMissingOverride(level);
    }

    /**
     * @param reportPath
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setReportPath(java.lang.String)
     */
    public void setReportPath(final String reportPath) {
        this.compilerOptions.setReportPath(reportPath);
    }

    /**
     * @param level
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setReportUnknownTypes(com.google.javascript.jscomp.CheckLevel)
     */
    public void setReportUnknownTypes(final CheckLevel level) {
        this.compilerOptions.setReportUnknownTypes(level);
    }

    /**
     * @param reserveRawExports
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setReserveRawExports(boolean)
     */
    public void setReserveRawExports(final boolean reserveRawExports) {
        this.compilerOptions.setReserveRawExports(reserveRawExports);
    }

    /**
     * @param rewriteFunctionExpressions
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setRewriteFunctionExpressions(boolean)
     */
    public void setRewriteFunctionExpressions(
            final boolean rewriteFunctionExpressions) {
        this.compilerOptions
                .setRewriteFunctionExpressions(rewriteFunctionExpressions);
    }

    /**
     * @param rewrite
     * @deprecated
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setRewriteNewDateGoogNow(boolean)
     */
    @Deprecated
    public void setRewriteNewDateGoogNow(final boolean rewrite) {
        this.compilerOptions.setRewriteNewDateGoogNow(rewrite);
    }

    /**
     * @param runtimeTypeCheck
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setRuntimeTypeCheck(boolean)
     */
    public void setRuntimeTypeCheck(final boolean runtimeTypeCheck) {
        this.compilerOptions.setRuntimeTypeCheck(runtimeTypeCheck);
    }

    /**
     * @param runtimeTypeCheckLogFunction
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setRuntimeTypeCheckLogFunction(java.lang.String)
     */
    public void setRuntimeTypeCheckLogFunction(
            final String runtimeTypeCheckLogFunction) {
        this.compilerOptions
                .setRuntimeTypeCheckLogFunction(runtimeTypeCheckLogFunction);
    }

    /**
     * @param save
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setSaveDataStructures(boolean)
     */
    public void setSaveDataStructures(final boolean save) {
        this.compilerOptions.setSaveDataStructures(save);
    }

    /**
     * @param shadow
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setShadowVariables(boolean)
     */
    public void setShadowVariables(final boolean shadow) {
        this.compilerOptions.setShadowVariables(shadow);
    }

    /**
     * @param skipAllPasses
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setSkipAllPasses(boolean)
     */
    public void setSkipAllPasses(final boolean skipAllPasses) {
        this.compilerOptions.setSkipAllPasses(skipAllPasses);
    }

    /**
     * @param smartNameRemoval
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setSmartNameRemoval(boolean)
     */
    public void setSmartNameRemoval(final boolean smartNameRemoval) {
        this.compilerOptions.setSmartNameRemoval(smartNameRemoval);
    }

    /**
     * @param sourceMapDetailLevel
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setSourceMapDetailLevel(com.google.javascript.jscomp.SourceMap.DetailLevel)
     */
    public void setSourceMapDetailLevel(final DetailLevel sourceMapDetailLevel) {
        this.compilerOptions.setSourceMapDetailLevel(sourceMapDetailLevel);
    }

    /**
     * @param sourceMapFormat
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setSourceMapFormat(com.google.javascript.jscomp.SourceMap.Format)
     */
    public void setSourceMapFormat(final Format sourceMapFormat) {
        this.compilerOptions.setSourceMapFormat(sourceMapFormat);
    }

    /**
     * @param sourceMapLocationMappings
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setSourceMapLocationMappings(java.util.List)
     */
    public void setSourceMapLocationMappings(
            final List<LocationMapping> sourceMapLocationMappings) {
        this.compilerOptions
                .setSourceMapLocationMappings(sourceMapLocationMappings);
    }

    /**
     * @param sourceMapOutputPath
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setSourceMapOutputPath(java.lang.String)
     */
    public void setSourceMapOutputPath(final String sourceMapOutputPath) {
        this.compilerOptions.setSourceMapOutputPath(sourceMapOutputPath);
    }

    /**
     * @param enabled
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setSpecializeInitialModule(boolean)
     */
    public void setSpecializeInitialModule(final boolean enabled) {
        this.compilerOptions.setSpecializeInitialModule(enabled);
    }

    /**
     * @param stripNamePrefixes
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setStripNamePrefixes(java.util.Set)
     */
    public void setStripNamePrefixes(final Set<String> stripNamePrefixes) {
        this.compilerOptions.setStripNamePrefixes(stripNamePrefixes);
    }

    /**
     * @param stripNameSuffixes
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setStripNameSuffixes(java.util.Set)
     */
    public void setStripNameSuffixes(final Set<String> stripNameSuffixes) {
        this.compilerOptions.setStripNameSuffixes(stripNameSuffixes);
    }

    /**
     * @param stripTypePrefixes
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setStripTypePrefixes(java.util.Set)
     */
    public void setStripTypePrefixes(final Set<String> stripTypePrefixes) {
        this.compilerOptions.setStripTypePrefixes(stripTypePrefixes);
    }

    /**
     * @param stripTypes
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setStripTypes(java.util.Set)
     */
    public void setStripTypes(final Set<String> stripTypes) {
        this.compilerOptions.setStripTypes(stripTypes);
    }

    /**
     * @param summaryDetailLevel
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setSummaryDetailLevel(int)
     */
    public void setSummaryDetailLevel(final int summaryDetailLevel) {
        this.compilerOptions.setSummaryDetailLevel(summaryDetailLevel);
    }

    /**
     * @param syntheticBlockEndMarker
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setSyntheticBlockEndMarker(java.lang.String)
     */
    public void setSyntheticBlockEndMarker(final String syntheticBlockEndMarker) {
        this.compilerOptions
                .setSyntheticBlockEndMarker(syntheticBlockEndMarker);
    }

    /**
     * @param syntheticBlockStartMarker
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setSyntheticBlockStartMarker(java.lang.String)
     */
    public void setSyntheticBlockStartMarker(
            final String syntheticBlockStartMarker) {
        this.compilerOptions
                .setSyntheticBlockStartMarker(syntheticBlockStartMarker);
    }

    /**
     * @param tighten
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setTightenTypes(boolean)
     */
    public void setTightenTypes(final boolean tighten) {
        this.compilerOptions.setTightenTypes(tighten);
    }

    /**
     * @param tracer
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setTracer(com.google.javascript.jscomp.CompilerOptions.TracerMode)
     */
    public void setTracer(final TracerMode tracer) {
        this.compilerOptions.setTracer(tracer);
    }

    /**
     * @param mode
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setTracerMode(com.google.javascript.jscomp.CompilerOptions.TracerMode)
     */
    public void setTracerMode(final TracerMode mode) {
        this.compilerOptions.setTracerMode(mode);
    }

    /**
     * @param transformAMDToCJSModules
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setTransformAMDToCJSModules(boolean)
     */
    public void setTransformAMDToCJSModules(
            final boolean transformAMDToCJSModules) {
        this.compilerOptions
                .setTransformAMDToCJSModules(transformAMDToCJSModules);
    }

    /**
     * @param tweakProcessing
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setTweakProcessing(com.google.javascript.jscomp.CompilerOptions.TweakProcessing)
     */
    public void setTweakProcessing(final TweakProcessing tweakProcessing) {
        this.compilerOptions.setTweakProcessing(tweakProcessing);
    }

    /**
     * @param tweakReplacements
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setTweakReplacements(java.util.Map)
     */
    public void setTweakReplacements(final Map<String, Object> tweakReplacements) {
        this.compilerOptions.setTweakReplacements(tweakReplacements);
    }

    /**
     * @param tweakId
     * @param value
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setTweakToBooleanLiteral(java.lang.String,
     * boolean)
     */
    public void setTweakToBooleanLiteral(final String tweakId,
            final boolean value) {
        this.compilerOptions.setTweakToBooleanLiteral(tweakId, value);
    }

    /**
     * @param tweakId
     * @param value
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setTweakToDoubleLiteral(java.lang.String,
     * double)
     */
    public void setTweakToDoubleLiteral(final String tweakId, final double value) {
        this.compilerOptions.setTweakToDoubleLiteral(tweakId, value);
    }

    /**
     * @param tweakId
     * @param value
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setTweakToNumberLiteral(java.lang.String,
     * int)
     */
    public void setTweakToNumberLiteral(final String tweakId, final int value) {
        this.compilerOptions.setTweakToNumberLiteral(tweakId, value);
    }

    /**
     * @param tweakId
     * @param value
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setTweakToStringLiteral(java.lang.String,
     * java.lang.String)
     */
    public void setTweakToStringLiteral(final String tweakId, final String value) {
        this.compilerOptions.setTweakToStringLiteral(tweakId, value);
    }

    /**
     * @param names
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setUnaliasableGlobals(java.lang.String)
     */
    public void setUnaliasableGlobals(final String names) {
        this.compilerOptions.setUnaliasableGlobals(names);
    }

    /**
     * @param variableRenaming
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setVariableRenaming(com.google.javascript.jscomp.VariableRenamingPolicy)
     */
    public void setVariableRenaming(
            final VariableRenamingPolicy variableRenaming) {
        this.compilerOptions.setVariableRenaming(variableRenaming);
    }

    /**
     * @param type
     * @param level
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setWarningLevel(com.google.javascript.jscomp.DiagnosticGroup,
     * com.google.javascript.jscomp.CheckLevel)
     */
    public void setWarningLevel(final DiagnosticGroup type,
            final CheckLevel level) {
        this.compilerOptions.setWarningLevel(type, level);
    }

    /**
     * @param warningsGuard
     * @see
     * com.google.javascript.jscomp.CompilerOptions#setWarningsGuard(com.google.javascript.jscomp.ComposeWarningsGuard)
     */
    public void setWarningsGuard(final ComposeWarningsGuard warningsGuard) {
        this.compilerOptions.setWarningsGuard(warningsGuard);
    }

    /**
     * @see
     * com.google.javascript.jscomp.CompilerOptions#shouldColorizeErrorOutput()
     */
    public boolean shouldColorizeErrorOutput() {
        return this.compilerOptions.shouldColorizeErrorOutput();
    }

    /**
     * @see com.google.javascript.jscomp.CompilerOptions#skipAllCompilerPasses()
     */
    public void skipAllCompilerPasses() {
        this.compilerOptions.skipAllCompilerPasses();
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.compilerOptions.toString();
    }

}
