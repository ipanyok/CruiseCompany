package dao.daofactory;

import entity.Bucket;
import java.util.List;

public interface IBucketDAO {
    Bucket findById(int id);
    List<Bucket> findByUserId(int idUser);
    void deleteBucketById(int id);
    String createBucket(Bucket bucket);
}
