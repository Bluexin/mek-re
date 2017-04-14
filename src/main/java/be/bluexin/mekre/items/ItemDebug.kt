package be.bluexin.mekre.items

import be.bluexin.mekre.blocks.MachineBlock
import be.bluexin.mekre.tiles.OperatingTE
import be.bluexin.saomclib.onClient
import be.bluexin.saomclib.onServer
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
object ItemDebug : MItem("debug") {

    override fun onItemUse(stack: ItemStack, playerIn: EntityPlayer, worldIn: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult {
        worldIn onClient {
            val b = worldIn.getBlockState(pos)
            if (b.block is MachineBlock) {
                val te = worldIn.getTileEntity(pos)
                if (te is OperatingTE) println("Inspecting TE: energy = ${te.energyStored}")
            }
        }

        worldIn onServer {
            val b = worldIn.getBlockState(pos)
            if (b.block is MachineBlock) {
                (worldIn.getTileEntity(pos) as? OperatingTE)?.receiveEnergy(10, false)
            }
        }

        return EnumActionResult.SUCCESS
    }
}
