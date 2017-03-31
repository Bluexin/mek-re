package be.bluexin.mekre.common.blocks.states

import be.bluexin.mekre.common.MachineType
import be.bluexin.mekre.common.MetalType
import be.bluexin.mekre.common.OreType
import be.bluexin.mekre.common.blocks.MachineBlock
import be.bluexin.mekre.common.blocks.MetalBlock
import be.bluexin.mekre.common.blocks.Ore
import net.minecraft.block.BlockHorizontal
import net.minecraft.block.properties.PropertyBool
import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.util.IStringSerializable

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * These can **NOT** be replaced with objects, because that'd cause a issue with blocks loading.
 *
 * @author Bluexin
 */
class BSOre(ore: Ore) : BlockStateContainer(ore, typeProperty) {

    companion object {
        val typeProperty = PropertyEnumWrapper(PropertyEnum.create<OreType>("type", OreType::class.java)!!)
    }
}

class BSMetalBlock(metalBlock: MetalBlock) : BlockStateContainer(metalBlock, typeProperty) {
    companion object {
        val typeProperty = PropertyEnumWrapper(PropertyEnum.create<MetalType>("type", MetalType::class.java, { it!!.hasBlockForm })!!)
    }
}

class BSMachine(machineBlock: MachineBlock) : BlockStateContainer(machineBlock, typeProperty.withFilter { it!!.ordinal >= machineBlock.subID * 16 && it.ordinal < (machineBlock.subID + 1) * 16 }, facingProperty, activeProperty) {
    companion object {
        val typeProperty = PropertyEnumWrapper(PropertyEnum.create<MachineType>("type", MachineType::class.java)!!)
        val facingProperty = PropertyEnumWrapper(BlockHorizontal.FACING, isItemIdx = false)
        val activeProperty = PropertyBoolWrapper(PropertyBool.create("active"))
    }

    init {
        @Suppress("UNCHECKED_CAST")
        machineBlock.typeProperty = getProperty("type") as PropertyEnum<MachineType>
//        println("Made ${machineBlock.typeProperty} for machine block # ${machineBlock.subID}")
    }
}

class PropertyEnumWrapper<T>(val property: PropertyEnum<T>, override val renderDepends: Boolean = true, override val isItemIdx: Boolean = true) : PropertyEnum<T>(property.name, property.valueClass, property.allowedValues), IPropertyWrapper where T : Enum<T>, T : IStringSerializable {
    override fun getName(value: T) = property.getName(value)!!

    override fun parseValue(value: String?) = property.parseValue(value)!!

    override fun getAllowedValues(): Collection<T> = property.allowedValues

    override fun equals(other: Any?) = property == other

    override fun hashCode() = property.hashCode()

    fun withFilter(predicate: (T?) -> Boolean) = PropertyEnumWrapper(PropertyEnum.create<T>(this.property.name, this.property.valueClass, predicate))
}

class PropertyBoolWrapper(val property: PropertyBool, override val renderDepends: Boolean = true) : PropertyBool(property.name), IPropertyWrapper {
    override fun getName(value: Boolean) = property.getName(value)!!

    override fun parseValue(value: String?) = property.parseValue(value)!!

    override fun getAllowedValues(): Collection<Boolean> = property.allowedValues

    override fun equals(other: Any?) = property == other

    override fun hashCode() = property.hashCode()

    override val isItemIdx = false
}

interface IPropertyWrapper {
    val renderDepends: Boolean
    val isItemIdx: Boolean
}
