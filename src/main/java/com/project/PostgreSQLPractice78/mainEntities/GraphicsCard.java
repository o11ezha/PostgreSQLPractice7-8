package com.project.PostgreSQLPractice78.mainEntities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "graphics_card")
public class GraphicsCard {

    @Id
    private Integer graphicsCardCode;
    @ManyToOne
    @JoinColumn(name = "contract_code", referencedColumnName = "contract_code")
    private Contract contractCode;
    private String graphicsCardName;
    private Date graphicsCardDate;
    private String Core;
    private Integer memoryStorage;
    private Integer cpuFrequencyMin;
    private Integer cpuFrequencyMax;
    private Integer graphicsCardCost;
    private Integer shaderAmount;
    private Integer fillRatePixel;
    private Integer fillRateFexel;

    public int getGraphicsCardCode() {
        return graphicsCardCode;
    }

    public Contract getContractCode() {
        return contractCode;
    }

    public String getGraphicsCardName() {
        return graphicsCardName;
    }

    public Date getGraphicsCardDate() {
        return graphicsCardDate;
    }

    public String getCore() {
        return Core;
    }

    public Integer getMemoryStorage() {
        return memoryStorage;
    }

    public Integer getCpuFrequencyMin() {
        return cpuFrequencyMin;
    }

    public Integer getCpuFrequencyMax() {
        return cpuFrequencyMax;
    }

    public Integer getGraphicsCardCost() {
        return graphicsCardCost;
    }

    public Integer getShaderAmount() {
        return shaderAmount;
    }

    public Integer getFillRatePixel() {
        return fillRatePixel;
    }

    public Integer getFillRateFexel() {
        return fillRateFexel;
    }
}
