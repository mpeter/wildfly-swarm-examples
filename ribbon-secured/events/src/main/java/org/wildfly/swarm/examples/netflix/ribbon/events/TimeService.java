package org.wildfly.swarm.examples.netflix.ribbon.events;

import com.netflix.ribbon.Ribbon;
import com.netflix.ribbon.RibbonRequest;
import com.netflix.ribbon.proxy.annotation.Http;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.netflix.ribbon.proxy.annotation.ResourceGroup;
import com.netflix.ribbon.proxy.annotation.TemplateName;
import io.netty.buffer.ByteBuf;
import org.wildfly.swarm.netflix.ribbon.secured.client.SecuredRibbon;

import java.sql.Time;

/**
 * @author Bob McWhirter
 */
@ResourceGroup( name="time" )
public interface TimeService {

    TimeService INSTANCE = SecuredRibbon.from(TimeService.class);

    @TemplateName("currentTime")
    @Http(
            method = Http.HttpMethod.GET,
            uri = "/"
    )
    @Hystrix(
            fallbackHandler = TimeFallbackHandler.class
    )
    RibbonRequest<ByteBuf> currentTime();

}
