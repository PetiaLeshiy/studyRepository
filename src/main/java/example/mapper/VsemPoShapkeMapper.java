package example.mapper;

import com.mysql.cj.jdbc.Blob;
import example.model.VsemPoShapkeModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VsemPoShapkeMapper implements RowMapper<VsemPoShapkeModel> {

    public static final String sql = "SELECT maintable.id, maintable.textile_name, maintable.textile, maintable.thickness, maintable.color, maintable.picture_type from maintable";
    @Override
    public VsemPoShapkeModel mapRow(ResultSet resultSet, int i) throws SQLException {
        int color = resultSet.getInt("color");
        int id = resultSet.getInt("id");
        int picture_type = resultSet.getInt("picture_type");
        Blob textile = (Blob) resultSet.getBlob("textile");
        String textile_name = resultSet.getString("textile_name");
        int thickness = resultSet.getInt("thickness");
        return new VsemPoShapkeModel(id, textile_name,textile, thickness,color, picture_type);
    }
}
