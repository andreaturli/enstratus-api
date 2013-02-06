package com.enstratus.api;

/**
 * The x-es-details header specifies the level of response detail you would
 * like. It can have the values “none”, “basic”, or “extended”. The default is
 * “none”. When “none”, only resource IDs are provided in the response. When
 * basic, all basic fields are included in the response, but relationships are
 * specified only through IDs. For example, the relationship of a region to a
 * cloud would be provided by a <cloud cloudId=”1”/> entry in the
 * <region></region> body. Extended, on the other hand, indicates that
 * relationships should have the basic resource details embedded in their
 * bodies. Clients should request an extended response only when absolutely
 * necessary is it impacts performance both for your application and enStratus
 * servers.
 * 
 * @author andrea
 * 
 */
public enum Details {
    NONE, BASIC, EXTENDED
}
