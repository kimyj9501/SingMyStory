<div id="kboard-ocean-rating-list">
	<div class="kboard-header">
		<?php if($board->use_category == 'yes'):?>
		<div class="kboard-category">
			<?php if($board->initCategory1()):?>
				<ul class="kboard-category-list">
					<li<?php if(!$_GET['category1']):?> class="kboard-category-selected"<?php endif?>><a href="<?php echo $url->set('category1', '')->set('pageid', '1')->set('target', '')->set('keyword', '')->set('mod', 'list')->tostring()?>"><?php echo __('All', 'kboard')?></a></li>
					<?php while($board->hasNextCategory()):?>
					<li<?php if($_GET['category1'] == $board->currentCategory()):?> class="kboard-category-selected"<?php endif?>><a href="<?php echo $url->set('category1', $board->currentCategory())->set('pageid', '1')->set('target', '')->set('keyword', '')->set('mod', 'list')->toString()?>"><?php echo $board->currentCategory()?></a></li>
					<?php endwhile?>
				</ul>
			<?php endif?>
			
			<?php if($board->initCategory2()):?>
				<ul class="kboard-category-list">
					<li<?php if(!$_GET['category2']):?> class="kboard-category-selected"<?php endif?>><a href="<?php echo $url->set('category2', '')->set('pageid', '1')->set('target', '')->set('keyword', '')->set('mod', 'list')->tostring()?>"><?php echo __('All', 'kboard')?></a></li>
					<?php while($board->hasNextCategory()):?>
					<li<?php if($_GET['category2'] == $board->currentCategory()):?> class="kboard-category-selected"<?php endif?>><a href="<?php echo $url->set('category2', $board->currentCategory())->set('pageid', '1')->set('target', '')->set('keyword', '')->set('mod', 'list')->toString()?>"><?php echo $board->currentCategory()?></a></li>
					<?php endwhile?>
				</ul>
			<?php endif?>
		</div>
		<?php endif?>
	</div>
	
	<!-- 리스트 시작 -->
	<ul class="kboard-list">
	<?php while($content = $list->hasNext()):?>
		<li class="kboard-list-item">
			<div class="kboard-wrap-left"><?php echo get_avatar($content->member_uid, 70, $default, $content->member_display)?></div>
			<div class="kboard-wrap-center">
				<div class="kboard-item-title cut_strings"><a href="<?php echo $url->set('uid', $content->uid)->set('mod', 'document')->toString()?>"><?php echo $content->title?> <span class="kboard-rating value-<?php echo $content->option->rating?>" title="<?php echo $content->option->rating?>"></span></a></div>
				<div class="kbaord-item-rating"><span class="kboard-rating value-<?php echo $content->option->rating?>" title="<?php echo $content->option->rating?>"></span></div>
				<div class="kboard-item-content cut_strings"><a href="<?php echo $url->set('uid', $content->uid)->set('mod', 'document')->toString()?>"><?php echo strip_tags($content->content)?></a></div>
				<div class="kboard-item-info">
					<span><?php echo $content->member_display?></span> |
					<?php echo date("Y.m.d", strtotime($content->date))?>
				</div>
			</div>
			<div class="kboard-wrap-right">
				<div class="kboard-item-like">추천 : <span class="kboard-count-bold"><?php echo $content->like?></span></div>
				<div class="kboard-item-comment">답변 : <span class="kboard-count-bold"><?php $comment = $content->getCommentsCount('',''); echo $comment?$comment:'0';?></span></div>
				<div class="kboard-item-view">조회 : <span class="kboard-count-bold"><?php echo $content->view?></span></div>
			</div>
		</li>
	<?php endwhile?>
	</ul>
	<!-- 리스트 끝 -->
	
	<!-- 페이징 시작 -->
	<div class="kboard-pagination">
		<ul class="kboard-pagination-pages">
			<?php echo kboard_pagination($list->page, $list->total, $list->rpp)?>
		</ul>
	</div>
	<!-- 페이징 끝 -->
	
	<form id="kboard-search-form" method="get" action="<?php echo $url->set('mod', 'list')->toString()?>">
		<?php echo $url->set('pageid', '1')->set('target', '')->set('keyword', '')->set('mod', 'list')->toInput()?>
		<div class="kboard-search">
			<select name="target">
				<option value=""><?php echo __('All', 'kboard')?></option>
				<option value="title"<?php if($_GET['target'] == 'title'):?> selected="selected"<?php endif?>><?php echo __('Title', 'kboard')?></option>
				<option value="content"<?php if($_GET['target'] == 'content'):?> selected="selected"<?php endif?>><?php echo __('Content', 'kboard')?></option>
				<option value="member_display"<?php if($_GET['target'] == 'member_display'):?> selected="selected"<?php endif?>><?php echo __('Author', 'kboard')?></option>
			</select>
			<input type="text" name="keyword" value="<?php echo $_GET['keyword']?>">
			<button type="submit" class="kboard-ocean-rating-button-small"><?php echo __('Search', 'kboard')?></button>
		</div>
	</form>
	
	<?php if($board->isWriter()):?>
	<!-- 버튼 시작 -->
	<div class="kboard-control">
		<a href="<?php echo $url->set('mod', 'editor')->toString()?>" class="kboard-ocean-rating-button-small"><?php echo __('New', 'kboard')?></a>
	</div>
	<!-- 버튼 끝 -->
	<?php endif?>
	
	<div class="kboard-ocean-rating-poweredby">
		<a href="http://www.cosmosfarm.com/products/kboard" onclick="window.open(this.href); return false;" title="<?php echo __('KBoard is the best community software available for WordPress', 'kboard')?>">Powered by KBoard</a>
	</div>
</div>

<script type="text/javascript" src="<?php echo $skin_path?>/script.js"></script>