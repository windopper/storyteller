package Events;

import io.netty.channel.*;
import net.minecraft.network.protocol.game.PacketPlayInBlockDig;
import net.minecraft.network.protocol.game.PacketPlayInUseEntity;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.lang.reflect.Field;

public class InteractListener implements Listener {
    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event) {
        injectPlayer(event.getPlayer());
    }

    @EventHandler
    public void PlayerQuitEvent(PlayerQuitEvent event) {
        removePlayer(event.getPlayer());
    }

    private void removePlayer(Player player) {
        Channel channel = ((CraftPlayer) player).getHandle().b.a.k;
        channel.eventLoop().submit(() -> {
            channel.pipeline().remove(player.getName());
            return null;
        });
    }

    private void injectPlayer(Player player) {
        ChannelDuplexHandler channelDuplexHandler = new ChannelDuplexHandler() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object packet) throws Exception {
                if(packet instanceof PacketPlayInUseEntity packetPlayInUseEntity) {
                    Bukkit.broadcastMessage("1");
                }

                super.channelRead(ctx, packet);
            }

            @Override
            public void write(ChannelHandlerContext ctx, Object packet, ChannelPromise promise) throws Exception {
                if(packet instanceof PacketPlayInUseEntity packetPlayInUseEntity) {
                    int entityId = (int) getValue(packetPlayInUseEntity, "a");
                    Bukkit.broadcastMessage("2");
                }

                super.write(ctx, packet, promise);
            }
        };

        ChannelPipeline pipeline = ((CraftPlayer) player).getHandle().b.a.k.pipeline();
        pipeline.addBefore("packet_handler", player.getName(), channelDuplexHandler);
    }

    private Object getValue(Object object, String fieldName) {
        Object result = null;
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            result = field.get(object);
            field.setAccessible(false);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
