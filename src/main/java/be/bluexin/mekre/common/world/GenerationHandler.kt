package be.bluexin.mekre.common.world

import be.bluexin.mekre.common.blocks.MBlocks
import be.bluexin.mekre.common.blocks.OreType
import be.bluexin.mekre.common.blocks.states.BSOre
import net.minecraft.block.state.pattern.BlockMatcher
import net.minecraft.init.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.chunk.IChunkGenerator
import net.minecraft.world.chunk.IChunkProvider
import net.minecraft.world.gen.ChunkProviderEnd
import net.minecraft.world.gen.ChunkProviderHell
import net.minecraft.world.gen.feature.WorldGenMinable
import net.minecraftforge.fml.common.IWorldGenerator
import java.util.*

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
object GenerationHandler : IWorldGenerator {

    val osmium = 12 // TODO: change with config
    val copper = 16
    val tin = 14
    val salt = 2

    override fun generate(random: Random, chunkX: Int, chunkZ: Int, world: World, chunkGenerator: IChunkGenerator, chunkProvider: IChunkProvider) {
        if (chunkGenerator !is ChunkProviderHell && chunkGenerator !is ChunkProviderEnd) {
            (1..osmium).forEach {
                val pos = BlockPos(chunkX * 16 + random.nextInt(16), random.nextInt(60), chunkZ * 16 + random.nextInt(16))
                WorldGenMinable(MBlocks.ore.defaultState.withProperty(BSOre.typeProperty, OreType.OSMIUM), 8, BlockMatcher.forBlock(Blocks.STONE)).generate(world, random, pos)
            }

            (1..copper).forEach {
                val pos = BlockPos(chunkX * 16 + random.nextInt(16), random.nextInt(60), chunkZ * 16 + random.nextInt(16))
                WorldGenMinable(MBlocks.ore.defaultState.withProperty(BSOre.typeProperty, OreType.COPPER), 8, BlockMatcher.forBlock(Blocks.STONE)).generate(world, random, pos)
            }

            (1..tin).forEach {
                val pos = BlockPos(chunkX * 16 + random.nextInt(16), random.nextInt(60), chunkZ * 16 + random.nextInt(16))
                WorldGenMinable(MBlocks.ore.defaultState.withProperty(BSOre.typeProperty, OreType.TIN), 8, BlockMatcher.forBlock(Blocks.STONE)).generate(world, random, pos)
            }

            /*(1..salt).forEach {
                val randPosX = chunkX * 16 + random.nextInt(16)
                val randPosZ = chunkZ * 16 + random.nextInt(16)
                val pos = world.getTopSolidOrLiquidBlock(BlockPos(randPosX, 60, randPosZ))
                WorldGenSalt(6).generate(world, random, pos)
            }*/
        }
    }
}
