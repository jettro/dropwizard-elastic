package nl.gridshore.dwes.index.api;


import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Value object to pass a copy index request
 */
public class CopyIndexRequest {
    private String copyFrom;
    @NotNull
    private String name;
    private boolean copyOldData;
    private boolean removeOldIndices;
    private boolean removeOldAlias;
    private Map<String, String> mappings;
    private String settings;
    private String settingsIdentifier;
    private String mappingsIdentifier;
    private boolean useIndexAsExactName;

    public String getCopyFrom() {
        return copyFrom;
    }

    public void setCopyFrom(String copyFrom) {
        this.copyFrom = copyFrom;
    }

    public boolean isCopyOldData() {
        return copyOldData;
    }

    public void setCopyOldData(boolean copyOldData) {
        this.copyOldData = copyOldData;
    }

    public Map<String, String> getMappings() {
        return mappings;
    }

    public void setMappings(Map<String, String> mappings) {
        this.mappings = mappings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRemoveOldIndices() {
        return removeOldIndices;
    }

    public void setRemoveOldIndices(boolean removeOldIndices) {
        this.removeOldIndices = removeOldIndices;
    }

    public String getSettings() {
        return settings;
    }

    public void setSettings(String settings) {
        this.settings = settings;
    }

    public boolean isUseIndexAsExactName() {
        return useIndexAsExactName;
    }

    public void setUseIndexAsExactName(boolean useIndexAsExactName) {
        this.useIndexAsExactName = useIndexAsExactName;
    }

    public boolean isRemoveOldAlias() {
        return removeOldAlias;
    }

    public void setRemoveOldAlias(boolean removeOldAlias) {
        this.removeOldAlias = removeOldAlias;
    }

    public String getMappingsIdentifier() {
        return mappingsIdentifier;
    }

    public void setMappingsIdentifier(String mappingsIdentifier) {
        this.mappingsIdentifier = mappingsIdentifier;
    }

    public String getSettingsIdentifier() {
        return settingsIdentifier;
    }

    public void setSettingsIdentifier(String settingsIdentifier) {
        this.settingsIdentifier = settingsIdentifier;
    }

    @Override
    public String toString() {
        return "CopyIndexRequest{" +
                "copyFrom='" + copyFrom + '\'' +
                ", name='" + name + '\'' +
                ", copyOldData=" + copyOldData +
                ", removeOldIndices=" + removeOldIndices +
                ", removeOldAlias=" + removeOldAlias +
                ", settingsIdentifier='" + settingsIdentifier + '\'' +
                ", mappingsIdentifier='" + mappingsIdentifier + '\'' +
                ", useIndexAsExactName=" + useIndexAsExactName +
                '}';
    }
}
