package foxx.venson.foxyjumpscare.client

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext
import net.minecraft.text.Text
import net.minecraft.util.Identifier

class FoxyjumpscareClient : ClientModInitializer {

    // Point to src/main/resources/assets/your_mod_id/textures/gui/my_image.png
    private val myImage = Identifier("foxyjumpscare", "textures/gui/fox.jpg")

    // User editable settings.
    val forceGuiClosed = false

    val filepath = "textures/foxy_jumpscare/frame"
    val frameammount = 14
    var frames = emptyArray<Identifier>()


    override fun onInitializeClient() {
        // Load each frame on Initialize.
        for (i in 1..<frameammount) {
            frames += Identifier("foxyjumpscare", "$filepath$i.png")
            MinecraftClient.getInstance().player?.sendMessage(Text.literal("Printing frame $i"), true)
        }

        HudRenderCallback.EVENT.register(HudRenderCallback { drawContext, tickDelta ->
            doJumpscare(drawContext)
        })
    }

    private fun doJumpscare(context: DrawContext) {
        val client = MinecraftClient.getInstance()

        // Get the display size.
        val textureWidth = client.window.scaledWidth
        val textureHeight = client.window.scaledHeight


        // If a gui menu is open, close it.
        if (forceGuiClosed) client.currentScreen?.close()

        context.drawTexture(
            frames[0],
            0, 0,
            0f, 0f,
            textureWidth, textureHeight,
            textureWidth, textureHeight
        )

    }
}
