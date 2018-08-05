package by.rudenkodv.library.books.data.ws;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RefreshScope
@RestController
public class BookDataApplication {

	@Autowired
	private GridFsOperations gridOperations;

	// this variable is used to store ImageId for other actions like: findOne or delete
	private ObjectId imageFileId;

	@GetMapping("/save/image")
	public String saveImage() throws FileNotFoundException {

		DBObject metaData = new BasicDBObject();
		metaData.put("bookId", "id");
		metaData.put("type", "image");

		InputStream iamgeStream = new FileInputStream("D:\\JSA\\jsa-logo.png");

		// Store file to MongoDB
		ObjectId imageFileId = gridOperations.store(iamgeStream, "jsa-logo.png", "image/png", metaData);
		System.out.println("ImageFileId = " + imageFileId);
		return "Done";
	}

	@GetMapping("/save/book/content")
	public String saveBook() throws FileNotFoundException {

		DBObject metaData = new BasicDBObject();
		metaData.put("bookId", "id");
		metaData.put("type", "data");

		InputStream iamgeStream = new FileInputStream("D:\\JSA\\jsa-logo.png");

		// Store file to MongoDB
		ObjectId imageFileId = gridOperations.store(iamgeStream, "jsa-logo.png", "image/png", metaData);
		System.out.println("ImageFileId = " + imageFileId);
		return "Done";
	}

	@GetMapping("/get/image")
	public String getImageFile() throws IOException {
		// read file from MongoDB
		GridFSFile img = gridOperations.findOne(new Query(Criteria.where("_id").is(imageFileId)));

		// Save file back to local disk
		//img.writeTo("D:\\JSA\\retrieve\\jsa-logo.png");

		System.out.println("Image File Name:" + img.getFilename());

		return "Done";
	}

	@GetMapping("/get/book/content")
	public String getBookContent() throws IOException{
		// read file from MongoDB
		GridFSFile book = gridOperations.findOne(new Query(Criteria.where("_id").is(imageFileId)));

		// Save file back to local disk
		//imageFile.writeTo("D:\\JSA\\retrieve\\jsa-logo.png");

		//System.out.println("Image File Name:" + imageFile.getFilename());

		return "Done";
	}




}
