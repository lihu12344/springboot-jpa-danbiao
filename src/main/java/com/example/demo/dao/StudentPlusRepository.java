package com.example.demo.dao;

import com.example.demo.pojo.Student;
import com.example.demo.pojo.StudentCount;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentPlusRepository {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EntityManager entityManager;

    public List<Tuple> count(){
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> query=criteriaBuilder.createTupleQuery();

        Root<Student> root=query.from(Student.class);

        query.multiselect(root.get("age").alias("age"),criteriaBuilder.count(root).alias("count"));
        query.groupBy(root.get("age"));

        TypedQuery<Tuple> tupleTypedQuery=entityManager.createQuery(query);
        return tupleTypedQuery.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<StudentCount> count2(){
        String sql="select age,count(*) as count from student group by age";
        Query query=entityManager.createNativeQuery(sql).setFirstResult(1).setMaxResults(3);

        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(StudentCount.class)).getResultList();
        return query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(StudentCount.class)).getResultList();
    }

    public List<Student> find(String name,Integer age){
        Specification<Student> specification= (Specification<Student>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates=new ArrayList<>();

            if(name!=null){
                predicates.add(criteriaBuilder.like(root.get("name"),name));
            }

            if(age!=null){
                predicates.add(criteriaBuilder.equal(root.get("age"),age));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };

        return studentRepository.findAll(specification);
    }
}
