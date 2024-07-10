package test;


import org.junit.Assert;
import org.junit.Test;
import parser.ColumnToken;
import parser.ParserResult;
import parser.SqlStatementParser;

import java.util.Set;

import static org.junit.Assert.fail;

public class SqlStatementParserTest {

    @Test
    public void testWithStatement() {
        String sql = "with y as (SELECT * FROM x.tbl_a) select a,b,c,DATETOSTRING(date_attr_name,'mm/dd/yyyy') from y";
        SqlStatementParser parser = new SqlStatementParser();
        ParserResult result = parser.parseTokens(sql);
        if (result.isSuccess()) {
            Set tokens=result.getTokens();
            if (!tokens.contains(ColumnToken.withColumn("","x","tbl_a","date_attr_name"))) {
                fail("Did not parse x.tbl_a.date_attr_name.");
            }
            if (!tokens.contains(ColumnToken.withColumn("","x","tbl_a","a"))) {
                fail("Did not parse x.tbl_a.a.");
            }
            if (!tokens.contains(ColumnToken.withColumn("","x","tbl_a","b"))) {
                fail("Did not parse x.tbl_a.b.");
            }
            if (!tokens.contains(ColumnToken.withColumn("","x","tbl_a","c"))) {
                fail("Did not parse x.tbl_a.c.");
            }
            if (!tokens.contains(ColumnToken.withColumn("","x","tbl_a","*"))) {
                fail("Did not parse x.tbl_a.*.");
            }
            if (!tokens.contains(ColumnToken.withTable("","x","tbl_a"))) {
                fail("Did not parse x.tbl_a.");
            }
            Assert.assertTrue("Parsed properly.",true);
            // You can also add assertions here to validate the parsing result
            // For example, you can check that specific tokens or structures were parsed correctly.
        } else {
            System.out.println(result.getErrMsg());
            // Fail the test if parsing is unsuccessful
            fail("SQL statement parsing failed");
        }
    }


    @Test
    public void testSimpleStatement() {
        String sql = "select a,b,c,DATETOSTRING(date_attr_name,'mm/dd/yyyy') from x.tbl_a";
        SqlStatementParser parser = new SqlStatementParser();
        ParserResult result = parser.parseTokens(sql);
        if (result.isSuccess()) {
            Set tokens=result.getTokens();
            if (!tokens.contains(ColumnToken.withColumn("","x","tbl_a","date_attr_name"))) {
                fail("Did not parse x.tbl_a.date_attr_name.");
            }
            if (!tokens.contains(ColumnToken.withColumn("","x","tbl_a","a"))) {
                fail("Did not parse x.tbl_a.a.");
            }
            if (!tokens.contains(ColumnToken.withColumn("","x","tbl_a","b"))) {
                fail("Did not parse x.tbl_a.b.");
            }
            if (!tokens.contains(ColumnToken.withColumn("","x","tbl_a","c"))) {
                fail("Did not parse x.tbl_a.c.");
            }
            if (!tokens.contains(ColumnToken.withTable("","x","tbl_a"))) {
                fail("Did not parse x.tbl_a.");
            }
            Assert.assertTrue("Parsed properly.",true);
            // You can also add assertions here to validate the parsing result
            // For example, you can check that specific tokens or structures were parsed correctly.
        } else {
            System.out.println(result.getErrMsg());
            // Fail the test if parsing is unsuccessful
            fail("SQL statement parsing failed");
        }
    }
}