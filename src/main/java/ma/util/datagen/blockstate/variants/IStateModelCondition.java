package ma.util.datagen.blockstate.variants;

import ma.util.datagen.blockstate.Model;

@FunctionalInterface
public interface IStateModelCondition {
    /**
     * 获取每个方块状态对应的模型
     * @param state 方块状态
     * @return 模型
     * @see Model
     * @see VariantsState
     */
    Model getModel(VariantsState state);
}
