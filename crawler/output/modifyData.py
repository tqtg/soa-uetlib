with open('books.json', 'rb') as inFile:
	with open('newbooks.json', 'wb') as outFile:
		for line in inFile:
			line = line.replace('collection', 'category')
			line = line.replace('},', '\n\t},')
			line = line.replace('{"', '\n\t{\n\t\t"')
			line = line.replace(',"', ',\n\t\t"')
			line = line.replace('":"', '" : "')
			line = line.replace('"page":', '"page" : ')
			line = line.replace('"}]', '"\n\t}\n]')
			outFile.write(line)