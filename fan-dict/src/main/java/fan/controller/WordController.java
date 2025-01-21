package fan.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import grey.fable.base.net.Response;
import fan.pojo.dto.WordDTO;
import fan.pojo.entity.WordDO;
import fan.pojo.query.WordQuery;
import fan.pojo.vo.WordVO;
import fan.service.WordService;
import fan.utils.DictMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Word controller
 *
 * @author Fan
 * @since 2024/3/27 16:22
 */
@RestController
@RequestMapping("/word")
public class WordController {

    private final WordService wordService;
    private final DictMapStruct dictMapStruct;

    @Autowired
    public WordController(WordService wordService, DictMapStruct dictMapStruct) {
        this.wordService = wordService;
        this.dictMapStruct = dictMapStruct;
    }

    @GetMapping("/pageWords")
    public Response<Page<WordVO>> pageWords(WordQuery wordQuery) {
        Page<WordDO> wordPage = wordService.pageWords(wordQuery);
        return Response.success(dictMapStruct.transWord(wordPage));
    }

    @GetMapping("/listWords")
    public Response<List<WordVO>> listWords(WordQuery wordQuery) {
        List<WordDO> wordVOS = wordService.listWords(wordQuery);
        return Response.success(dictMapStruct.transWord(wordVOS));
    }

    @PostMapping("/addWord")
    public Response<Integer> addWord(@RequestBody WordDTO wordDTO) {
        WordDO wordDO = dictMapStruct.transWord(wordDTO);
        return Response.success(wordService.addWord(wordDO));
    }

    @PutMapping("/updateWord")
    public Response<Integer> updateWord(@RequestBody WordDTO wordDTO) {
        WordDO wordDO = dictMapStruct.transWord(wordDTO);
        return Response.success(wordService.updateWord(wordDO));
    }

    @DeleteMapping("/batchDeleteWords/{id}")
    public Response<Integer> batchDeleteWords(@PathVariable("id") List<String> ids) {
        return Response.success(wordService.batchDeleteWords(ids));
    }
}