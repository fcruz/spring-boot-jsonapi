<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <GeocodeResponse>
            <formatted_address><xsl:value-of select="//result/formatted_address"/></formatted_address>
            <latitude><xsl:value-of select="//result/geometry/location/lat"/></latitude>
            <longitude><xsl:value-of select="//result/geometry/location/lng"/></longitude>
        </GeocodeResponse>
    </xsl:template>

</xsl:stylesheet>
