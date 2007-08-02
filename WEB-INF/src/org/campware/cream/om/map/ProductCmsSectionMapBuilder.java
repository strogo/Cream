package org.campware.cream.om.map;

import java.util.Date;
import java.math.BigDecimal;

import org.apache.torque.Torque;
import org.apache.torque.TorqueException;
import org.apache.torque.map.MapBuilder;
import org.apache.torque.map.DatabaseMap;
import org.apache.torque.map.TableMap;

/**
  *  This class was autogenerated by Torque on:
  *
  * [Fri Jan 26 03:28:50 CET 2007]
  *
  */
public class ProductCmsSectionMapBuilder implements MapBuilder
{
    /**
     * The name of this class
     */
    public static final String CLASS_NAME =
        "org.campware.cream.om.map.ProductCmsSectionMapBuilder";


    /**
     * The database map.
     */
    private DatabaseMap dbMap = null;

    /**
     * Tells us if this DatabaseMapBuilder is built so that we
     * don't have to re-build it every time.
     *
     * @return true if this DatabaseMapBuilder is built
     */
    public boolean isBuilt()
    {
        return (dbMap != null);
    }

    /**
     * Gets the databasemap this map builder built.
     *
     * @return the databasemap
     */
    public DatabaseMap getDatabaseMap()
    {
        return this.dbMap;
    }

    /**
     * The doBuild() method builds the DatabaseMap
     *
     * @throws TorqueException
     */
    public void doBuild() throws TorqueException
    {
        dbMap = Torque.getDatabaseMap("cream");

        dbMap.addTable("PRODUCT_CMS_SECTION");
        TableMap tMap = dbMap.getTable("PRODUCT_CMS_SECTION");

        tMap.setPrimaryKeyMethod(TableMap.NATIVE);


              tMap.addPrimaryKey("PRODUCT_CMS_SECTION.PRODUCT_CMS_SEC_ID", new Integer(0));
                    tMap.addForeignKey(
                "PRODUCT_CMS_SECTION.PRODUCT_ID", new Integer(0) , "PRODUCT" ,
                "PRODUCT_ID");
                    tMap.addColumn("PRODUCT_CMS_SECTION.CMS_PUBLICATION_ID", new Integer(0));
                    tMap.addColumn("PRODUCT_CMS_SECTION.CMS_SECTION_ID", new Integer(0));
                    tMap.addColumn("PRODUCT_CMS_SECTION.CMS_LANGUAGE_ID", new Integer(0));
          }
}
