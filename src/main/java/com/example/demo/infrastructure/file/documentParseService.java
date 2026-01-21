package com.example.demo.infrastructure.file;

import lombok.RequiredArgsConstructor;
import org.apache.tika.exception.TikaException;
import org.apache.tika.extractor.EmbeddedDocumentExtractor;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.bouncycastle.asn1.cms.MetaData;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@RequiredArgsConstructor
public class documentParseService {
    private static final int MAX_TEXT_LENGTH = 5 * 1024 * 1024;
    private final contentCleanService contentCleanService;
    //
    public String parse(MultipartFile file){
        try{
            String parse = parse(file.getInputStream());
            String cleanContent = contentCleanService.clean(parse);
            return cleanContent;
        } catch (TikaException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }
    public String parse(byte[] bytes){
        try{
            String parse = parse(new ByteArrayInputStream(bytes));
            String cleanContent = contentCleanService.clean(parse);
            return cleanContent;
        } catch (TikaException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }
    //使用tika解析输入流
    public String parse(InputStream inputStream) throws TikaException, IOException, SAXException {
        //自动检测解析器
        AutoDetectParser autoDetectParser = new AutoDetectParser();
        //内容处理器
        BodyContentHandler bodyContentHandler = new BodyContentHandler(MAX_TEXT_LENGTH);
        //元数据
        Metadata metaData = new Metadata();
        //解析上下文
        ParseContext parseContext = new ParseContext();
        parseContext.set(Parser.class,autoDetectParser);
        //禁用嵌入文档解析，避免提取图片引用
        //parseContext.set(EmbeddedDocumentExtractor.class,new N);
        //关闭图片提取
        //执行解析
        autoDetectParser.parse(inputStream,bodyContentHandler,metaData,parseContext);
        return bodyContentHandler.toString();
    }
}
