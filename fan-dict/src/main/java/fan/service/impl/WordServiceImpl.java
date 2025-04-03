package fan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import grey.fable.base.text.StringUtils;
import grey.fable.base.util.IdUtils;
import fan.dao.WordDAO;
import fan.pojo.entity.WordDO;
import fan.pojo.query.WordQuery;
import fan.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Word interface implementation class
 *
 * @author Fan
 * @since 2024/3/27 16:23
 */
@Service
public class WordServiceImpl implements WordService {

    private final WordDAO wordDAO;

    @Autowired
    public WordServiceImpl(WordDAO wordDAO) {
        this.wordDAO = wordDAO;
    }

    @Override
    public Page<WordDO> pageWords(WordQuery wordQuery) {
        LambdaQueryWrapper<WordDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(wordQuery.getEn()), WordDO::getEn, wordQuery.getEn())
                .like(StringUtils.isNotBlank(wordQuery.getCn()), WordDO::getCn, wordQuery.getCn())
                .eq(StringUtils.isNotBlank(wordQuery.getType()), WordDO::getType, wordQuery.getType())
                .orderByAsc(WordDO::getEn);
        return wordDAO.selectPage(new Page<>(wordQuery.getCurrentPage(), wordQuery.getPageSize()), queryWrapper);
    }

    @Override
    public List<WordDO> listWords(WordQuery wordQuery) {
        LambdaQueryWrapper<WordDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.likeRight(StringUtils.isNotBlank(wordQuery.getEn()), WordDO::getEn, wordQuery.getEn())
                .last("limit 5");
        return wordDAO.selectList(queryWrapper);
    }

    @Override
    public Integer addWord(WordDO wordDO) {
        wordDO.setId(String.valueOf(IdUtils.getSnowflakeId()));

        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        wordDO.setCreateTime(now);
        wordDO.setUpdateTime(now);
        return wordDAO.insert(wordDO);
    }

    @Override
    public Integer updateWord(WordDO wordDO) {
        wordDO.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        return wordDAO.updateById(wordDO);
    }

    @Override
    public Integer batchDeleteWords(List<String> ids) {
        return wordDAO.deleteByIds(ids);
    }
}